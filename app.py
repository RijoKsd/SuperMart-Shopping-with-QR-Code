from flask import Flask, render_template, request, redirect, session, jsonify
import datetime
from DBConnection import Database

app = Flask(__name__)
app.secret_key = "djljsdl"


# ---------------------------------------------- Global section started----------------------------------------------

# ********************************************** Logout ********************************************** 

@app.route('/logout')
def logout():
    session.clear()
    session['lin'] = '0'
    return redirect('/')


# ********************************************** Login ********************************************** 

@app.route('/', methods=['get', 'post'])
def login():
    if request.method == "POST":
        username = request.form['username']
        password = request.form['password']
        db = Database()
        query = db.selectOne(
            "select * from login where user_name = '" + username + "'and password = '" + password + "'")
        if query is not None:
            session['lin'] = '1'
            if query['user_type'] == 'admin':
                return redirect('/admin_home')
            elif query['user_type'] == 'shop':
                session['lid'] = query['login_id']
                return redirect('/shop_home')
            else:
                return '<script>alert("invalid credentials");window.location = "/"</script>'
        else:
            return '<script>alert("invalid username or password");window.location = "/"</script>'
    else:
        return render_template("index.html")


# ---------------------------------------------- Global section finished----------------------------------------------

# ---------------------------------------------- Admin section started -----------------------------------------------

# ********************************************** Verify Shop   **********************************************

@app.route('/verify_shop')
def verify_shop():
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select * from shop,login where shop.shop_id = login.login_id and login.user_type = 'pending'")
        return render_template("admin/verify_shop.html", data=data)
    return redirect('/')


# ********************************************** Approve Shop   **********************************************

@app.route('/approve/<shop_id>')
def approve(shop_id):
    if session['lin'] == "1":
        db = Database()
        db.update(
            "update login set user_type = 'shop' where login_id = '" + str(shop_id) + "'")
        return redirect('/verify_shop')
    return redirect('/')


# ********************************************** Reject Shop   **********************************************

@app.route('/reject/<shop_id>')
def reject(shop_id):
    db = Database()
    db.delete("delete from login where login_id = '" + str(shop_id) + "'")
    db.delete("delete from shop where shop_id = '" + str(shop_id) + "'")
    return redirect('/verify_shop')


# ********************************************** View approved Shop   **********************************************

@app.route('/view_approved_shop')
def view_approved_shop():
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select * from shop,login where shop.shop_id = login.login_id and (login.user_type = 'shop' or login.user_type='block')")
        return render_template("admin/view_approved_shop.html", data=data)
    return redirect('/')


# ********************************************** Block Shop   **********************************************

@app.route('/block/<shop_id>')
def block(shop_id):
    db = Database()
    db.update(
        "update login set user_type = 'block' where login_id = '" + str(shop_id) + "'")
    return redirect('/view_approved_shop')


# ********************************************** Unblock Shop   **********************************************

@app.route('/unblock/<shop_id>')
def unblock(shop_id):
    db = Database()
    db.update(
        "update login set user_type = 'shop' where login_id = '" + str(shop_id) + "'")
    return redirect('/view_approved_shop')


# ********************************************** Delete Shop   **********************************************

@app.route('/delete_shop/<shop_id>')
def delete_shop(shop_id):
    db = Database()
    db.delete("delete from login where login_id = '" + str(shop_id) + "'")
    db.delete("delete from shop where shop_id = '" + str(shop_id) + "'")
    db.delete("delete from product where shop_id = '" + str(shop_id) + "'")
    db.delete("delete from feedback where sender_id = '" + str(shop_id) + "'")
    db.delete("delete from complaint where user_id = '" + str(shop_id) + "'")
    db.delete("delete from rating where shop_id = '" + str(shop_id) + "'")
    return redirect('/view_approved_shop')


# ********************************************** View feedback   **********************************************


@app.route('/view_feedback', methods=['get', 'post'])
def view_feedback():
    if session['lin'] == "1":

        if request.method == "POST":
            select_option = request.form['select']
            if select_option == "user":
                db = Database()
                data = db.select(
                    "select * from feedback,user where user.user_id = feedback.sender_id ")
                return render_template("admin/view_feedback.html", data=data)
            else:
                db = Database()
                data = db.select(
                    "select * from feedback,shop where shop.shop_id = feedback.sender_id")
                return render_template("admin/view_feedback.html", data=data)
        else:
            return render_template("admin/view_feedback.html")
    return redirect('/')


# ********************************************** View complaint and send reply   **********************************************

@app.route('/view_complaint_send_reply', methods=['get', 'post'])
def view_complaint_send_reply():
    if session['lin'] == "1":
        if request.method == "POST":
            select_option = request.form['select']
            if select_option == "user":
                db = Database()
                data = db.select(
                    "select * from complaint,user where complaint.user_id = user.user_id")
                return render_template("admin/view_complaint_send_reply.html", data=data)
            else:
                db = Database()
                data = db.select(
                    "select * from complaint,shop where complaint.user_id = shop.shop_id")
                return render_template("admin/view_complaint_send_reply.html", data=data)
        else:
            return render_template("admin/view_complaint_send_reply.html")
    return redirect('/')


# ********************************************** View rating  **********************************************

@app.route('/view_rating')
def view_rating():
    if session['lin'] == "1":
        db = Database()
        res = db.select(
            "select rating.rating_id, rating.rating,user.user_id,shop.shop_id,user.name as un,shop.name as sn,rating.date from rating,user,shop where shop.shop_id = rating.shop_id and user.user_id = rating.user_id")
        ar_rt = []

        for im in range(0, len(res)):
            val = str(res[im]['rating'])
            ar_rt.append(val)
        fs = "/static/star/full.jpg"
        hs = "/static/star/half.jpg"
        es = "/static/star/empty.jpg"
        arr = []

        for rt in ar_rt:
            print(rt)
            a = float(rt)

            if a >= 0.0 and a < 0.4:
                print("eeeee")
                ar = [es, es, es, es, es]
                arr.append(ar)

            elif a >= 0.4 and a < 0.8:
                print("heeee")
                ar = [hs, es, es, es, es]
                arr.append(ar)

            elif a >= 0.8 and a < 1.4:
                print("feeee")
                ar = [fs, es, es, es, es]
                arr.append(ar)

            elif a >= 1.4 and a < 1.8:
                print("fheee")
                ar = [fs, hs, es, es, es]
                arr.append(ar)

            elif a >= 1.8 and a < 2.4:
                print("ffeee")
                ar = [fs, fs, es, es, es]
                arr.append(ar)

            elif a >= 2.4 and a < 2.8:
                print("ffhee")
                ar = [fs, fs, hs, es, es]
                arr.append(ar)

            elif a >= 2.8 and a < 3.4:
                print("fffee")
                ar = [fs, fs, fs, es, es]
                arr.append(ar)

            elif a >= 3.4 and a < 3.8:
                print("fffhe")
                ar = [fs, fs, fs, hs, es]
                arr.append(ar)

            elif a >= 3.8 and a < 4.4:
                print("ffffe")
                ar = [fs, fs, fs, fs, es]
                arr.append(ar)

            elif a >= 4.4 and a < 4.8:
                print("ffffh")
                ar = [fs, fs, fs, fs, hs]
                arr.append(ar)

            elif a >= 4.8 and a <= 5.0:
                print("fffff")
                ar = [fs, fs, fs, fs, fs]
                arr.append(ar)
            print(arr)
        return render_template("admin/view_rating.html", resu=res, r1=arr, ln=len(arr), data=res)
    return redirect('/')


# ********************************************** View user **********************************************

@app.route('/view_user')
def view_user():
    if session['lin'] == "1":
        db = Database()
        data = db.select("select * from user")
        return render_template("admin/view_user.html", data=data)
    return redirect('/')


# ********************************************** Admin dashboard **********************************************

@app.route('/admin_home')
def admin_home():
    return render_template("admin/admin_home.html")


@app.route('/reply/<complaint_id>', methods=['get', 'post'])
def reply(complaint_id):
    if request.method == "POST":
        reply = request.form['reply']
        db = Database()
        data = db.update(
            "update complaint set reply = '" + reply + "' ,reply_date = curdate() where complaint_id = '" + complaint_id + "'")
        return '<script>alert(" Reply send successfully");window.location = "/view_complaint_send_reply"</script>'
    else:
        return render_template('admin/reply.html')


# ---------------------------------------------- admin section finished ----------------------------------------------

# ---------------------------------------------- shop section started ----------------------------------------------

# ********************************************** Shop dashboard  **********************************************

@app.route('/shop_home')
def shop_home():
    if session['lin'] == "1":
        return render_template("shop/shop_home.html")
    return redirect('/')


# ********************************************** Shop register  **********************************************

@app.route('/register', methods=['post', 'get'])
def register():
    if request.method == "POST":
        shop_name = request.form['shop_name']
        place = request.form['place']
        pin = request.form['pin']
        email = request.form['email']
        phone = request.form['phone']
        image = request.files['image']
        date = datetime.datetime.now().strftime("%y%m%d-%H%M%S ")
        image.save(r"E:\QR shopping\python\static\images\\" + date + '.jpg')
        image_path = "/static/images/" + date + '.jpg'
        password = request.form['password']
        db = Database()
        login_id = db.insert(
            "insert into login values('','" + email + "','" + password + "','pending')")
        db.insert("insert into shop values('" + str(
            login_id) + "','" + shop_name + "','" + place + "','" + pin + "','" + email + "','" + phone + "','" + str(
            image_path) + "') ")
        return '<script>alert("Registered successfully completed");window.location="/"</script>'
    else:
        return render_template("shop/register.html")


# ********************************************** Add product **********************************************

@app.route('/add_product', methods=['post', 'get'])
def add_product():
    if session['lin'] == "1":
        if request.method == 'POST':
            product_name = request.form['product_name']
            price = request.form['price']
            details = request.form['details']
            image = request.files['image']
            # image path
            date = datetime.datetime.now().strftime("%y%m%d-%H%M%S ")
            image.save(r"E:\QR shopping\python\static\images\\" + date + '.jpg')
            image_path = "/static/images/" + date + '.jpg'
            db = Database()
            data = db.insert(
                "insert into product VALUE ('','" + product_name + "','" + price + "','" + details + "','" + str(
                    session['lid']) + "','" + image_path + "')")
            return '<script>alert("Added successfully ");window.location="/view_product"</script>'
        else:
            return render_template('shop/add_product.html')
    return redirect('/')


# ********************************************** View product **********************************************

@app.route('/view_product')
def view_product():
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select * from product where shop_id = '" + str(session['lid']) + "'")
        return render_template('shop/view_product.html', data=data)
    return redirect('/')


# ********************************************** Edit product **********************************************

@app.route('/edit_product/<product_id>', methods=['get', 'post'])
def edit_product(product_id):
    if session['lin'] == "1":

        if request.method == 'POST':
            product_name = request.form['product_name']
            price = request.form['price']
            details = request.form['details']
            image = request.files['image']
            # image path
            date = datetime.datetime.now().strftime("%y%m%d-%H%M%S ")
            image.save(r"E:\QR shopping\python\static\images\\" + date + '.jpg')
            image_path = "/static/images/" + date + '.jpg'
            if request.files != "":
                if image.filename != "":
                    db = Database()
                    db.update(
                        "update product set name='" + product_name + "',price = '" + price + "',details = '" + details + "',image = '" + image_path + "' where product_id = '" + product_id + "' ")
                    return '<script>alert("updated successfully  ");window.location="/view_product"</script>'
                else:
                    db = Database()
                    db.update(
                        "update product set name='" + product_name + "',price = '" + price + "',details = '" + details + "' where product_id = '" + product_id + "' ")
                    return '<script>alert("updated successfully  ");window.location="/view_product"</script>'
            else:
                db = Database()
                db.update(
                    "update product set name='" + product_name + "',price = '" + price + "',details = '" + details + "' where product_id = '" + product_id + "' ")
                return '<script>alert("updated successfully  ");window.location="/view_product"</script>'
        else:
            db = Database()
            data = db.selectOne(
                "select * from product where product_id = '" + product_id + "' ")
            return render_template("shop/update_product.html", data=data)
    return redirect('/')


# ********************************************** Delete product **********************************************

@app.route('/delete_product/<product_id>')
def delete_product(product_id):
    if session['lin'] == "1":
        db = Database()
        db.delete("delete from   product where product_id = '" + product_id + "'")
        return redirect('/view_product')
    return redirect('/')


# ********************************************** Add offer **********************************************

@app.route('/add_offer/<product_id>', methods=['post', 'get'])
def add_offer(product_id):
    if session['lin'] == "1":
        if request.method == 'POST':
            offer = request.form['offer']
            date_from = request.form['date_from']
            date_to = request.form['date_to']
            db = Database()
            res = db.selectOne(
                "select * from offer where product_id ='" + product_id + "'")
            if res is not None:
                db.update(
                    "update offer set offer='" + offer + "',date_from = '" + date_from + "',date_to ='" + date_to + "' where product_id = '" + product_id + "'")
                return '<script>alert("updated successfully  ");window.location="/view_product"</script>'
            else:

                db.insert("insert into offer values ('', '" + str(
                    product_id) + "','" + offer + "','" + date_from + "','" + date_to + "') ")
                return '<script>alert("added offer successfully  ");window.location="/view_product"</script>'
        else:
            return render_template("shop/add_offer.html")
    return redirect('/')


# ********************************************** View offer **********************************************

@app.route('/view_offer/<product_id>')
def view_offer(product_id):
    if session['lin'] == "1":
        db = Database()
        res = db.selectOne(
            "select * from offer where product_id = '" + product_id + "'")
        if res is not None:
            data = db.select(
                "select * from offer where product_id = '" + product_id + "'")
            return render_template("shop/view_offer.html", data=data)
        else:
            return '<script>alert("No offer found ");window.location="/view_product"</script>'
    return redirect('/')


# ********************************************** Edit offer **********************************************

@app.route('/edit_offer/<offer_id>', methods=['post', 'get'])
def edit_offer(offer_id):
    if session['lin'] == "1":
        if request.method == 'POST':
            offer = request.form['offer']
            date_from = request.form['date_from']
            date_to = request.form['date_to']
            db = Database()
            db.update(
                "update offer set offer = '" + offer + "',date_from = '" + date_from + "', date_to = '" + date_to + "' where offer_id = '" + offer_id + "'")
            return redirect('/view_product')
        else:
            db = Database()
            data = db.selectOne(
                "select * from offer where offer_id = '" + offer_id + "'")
            return render_template("shop/update_offer.html", data=data)
    return redirect('/')


# ********************************************** Delete offer **********************************************

@app.route('/delete_offer/<offer_id>')
def delete_offer(offer_id):
    db = Database()
    db.delete("delete from offer where offer_id = '" + offer_id + "'")
    return redirect('/view_product')


# ********************************************** Add stock **********************************************

@app.route('/add_stock', methods=['post', 'get'])
def add_stock():
    if session['lin'] == "1":
        if request.method == "POST":
            select_option = request.form['select']
            quantity = request.form['quantity']
            db = Database()

            res = db.selectOne(
                "select * from stock where product_id ='" + select_option + "'")
            if res is not None:
                db.update(
                    "update stock set quantity='" + quantity + "' where product_id = '" + select_option + "'")
                return '<script>alert("updated successfully  ");window.location="/view_stock"</script>'
            else:
                db.insert("insert into stock values ( '','" +
                          select_option + "','" + quantity + "') ")
                return '<script>alert("added successfully  ");window.location="/view_stock"</script>'
        else:
            db = Database()
            data = db.select(
                "select * from product where shop_id='" + str(session['lid']) + "'")
            return render_template("shop/add_stock.html", data=data)
    return redirect('/')


# ********************************************** View stock **********************************************

@app.route('/view_stock')
def view_stock():
    if session['lin'] == "1":
        db = Database()
        res = db.select(
            "select * from product,stock where product.product_id = stock.product_id  and shop_id= '" + str(
                session['lid']) + "' ")
        if res is not None:
            data = db.select(
                "select * from product,stock where product.product_id = stock.product_id  and shop_id= '" + str(
                    session['lid']) + "' ")
            return render_template("shop/view_stock.html", data=data)
        else:
            return '<script>alert("No stock found ");window.location="/view_product"</script>'
    return redirect('/')


# ********************************************** Update stock **********************************************

@app.route('/update_stock/<stock_id>', methods=['get', 'post'])
def update_stock(stock_id):
    if session['lin'] == "1":
        if request.method == "POST":
            select_option = request.form['select']
            quantity = request.form['quantity']
            db = Database()
            data = db.update("update stock set quantity = '" +
                             quantity + "' where stock_id = '" + stock_id + "'")
            return "<script>alert('Stock updated successfully');window.location = '/view_stock'</script>"
        else:
            db = Database()
            data = db.select(
                "select * from stock,product where stock.product_id = product.product_id and  stock_id = '" + stock_id + "'")
            return render_template("shop/update_stock.html", data=data)
    return redirect('/')


# ********************************************** Delete stock **********************************************

@app.route('/delete_stock/<stock_id>')
def delete_stock(stock_id):
    db = Database()
    db.delete("delete from   stock WHERE stock_id='" + stock_id + "'")
    return redirect('/view_stock')


# ********************************************** Send feedback **********************************************

@app.route('/send_feedback', methods=['post', 'get'])
def send_feedback():
    if session['lin'] == "1":
        if request.method == "POST":
            feedback = request.form['feedback']
            db = Database()
            db.insert("insert into feedback values ('','" +
                      str(session['lid']) + "','shop',curdate(),'" + feedback + "')")
            return '<script>alert("Feedback send successfully");window.location = "/shop_home"</script>'
        else:
            return render_template("shop/send_feedback.html")
    return redirect('/')


# ********************************************** Send complaint **********************************************

@app.route('/send_complaint', methods=['post', 'get'])
def send_complaint():
    if session['lin'] == "1":
        if request.method == "POST":
            complaint = request.form['complaint']
            db = Database()
            db.insert("insert into complaint values('','shop','" + str(
                session['lid']) + "','" + complaint + "',curdate(),'pending','pending' )")
            return '<script>alert("Complaint send successfully");window.location = "/shop_home"</script>'
        else:
            return render_template("shop/send_complaint.html")
    return redirect('/')


# ********************************************** View  complaints reply **********************************************

@app.route('/view_reply')
def view_reply():
    if session['lin'] == "1":
        db = Database()
        data = db.select("select * from complaint,shop where shop.shop_id = complaint.user_id and user_id = '" + str(
            session['lid']) + "'")

        return render_template("shop/view_reply.html", data=data)
    return redirect('/')


# ********************************************** View user rating to shop **********************************************

@app.route('/view_user_rating')
def view_user_rating():
    if session['lin'] == "1":
        db = Database()
        res = db.select(
            "select rating.date, user.name as un,rating.rating, shop.name as sn from rating,shop,user where user.user_id = rating.user_id and shop.shop_id = rating.shop_id and rating.shop_id = '" + str(
                session['lid']) + "'")

        ar_rt = []
        for im in range(0, len(res)):
            val = str(res[im]['rating'])
            ar_rt.append(val)
        fs = "/static/star/full.jpg"
        hs = "/static/star/half.jpg"
        es = "/static/star/empty.jpg"
        arr = []

        for rt in ar_rt:
            print(rt)
            a = float(rt)

            if a >= 0.0 and a < 0.4:
                print("eeeee")
                ar = [es, es, es, es, es]
                arr.append(ar)

            elif a >= 0.4 and a < 0.8:
                print("heeee")
                ar = [hs, es, es, es, es]
                arr.append(ar)

            elif a >= 0.8 and a < 1.4:
                print("feeee")
                ar = [fs, es, es, es, es]
                arr.append(ar)

            elif a >= 1.4 and a < 1.8:
                print("fheee")
                ar = [fs, hs, es, es, es]
                arr.append(ar)

            elif a >= 1.8 and a < 2.4:
                print("ffeee")
                ar = [fs, fs, es, es, es]
                arr.append(ar)

            elif a >= 2.4 and a < 2.8:
                print("ffhee")
                ar = [fs, fs, hs, es, es]
                arr.append(ar)

            elif a >= 2.8 and a < 3.4:
                print("fffee")
                ar = [fs, fs, fs, es, es]
                arr.append(ar)

            elif a >= 3.4 and a < 3.8:
                print("fffhe")
                ar = [fs, fs, fs, hs, es]
                arr.append(ar)

            elif a >= 3.8 and a < 4.4:
                print("ffffe")
                ar = [fs, fs, fs, fs, es]
                arr.append(ar)

            elif a >= 4.4 and a < 4.8:
                print("ffffh")
                ar = [fs, fs, fs, fs, hs]
                arr.append(ar)

            elif a >= 4.8 and a <= 5.0:
                print("fffff")
                ar = [fs, fs, fs, fs, fs]
                arr.append(ar)
            print(arr)

        return render_template("shop/view_rating.html", resu=res, r1=arr, ln=len(arr), data=res)
    return redirect('/')


# ********************************************** Viw bill **********************************************

@app.route('/view_bill')
def view_bill():
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select sum(product.price*bill.quantity) as total,user.name as un,user.*, bill_master.*,product.*,bill.* from bill_master,user,bill,product where bill_master.user_id=user.user_id and bill_master.shop_id='" + str(
                session['lid']) + "' and bill.master_id=bill_master.master_id and bill.product_id=product.product_id")
        # and bill_master.status = 'book'

        # data = db.select(
        #     "select user.name as un,user.*,bill_master.*,product.*,bill.* from bill_master,user,product,bill where bill_master.user_id = user.user_id and bill_master.master_id = bill.master_id and product.product_id = bill.product_id  and bill_master.shop_id = '" + str(
        #         session['lid']) + "'")
        return render_template("shop/view_bill.html", data=data)
    return redirect('/')


# ********************************************** View bill items **********************************************

@app.route('/view_bill_items/<master_id>')
def view_bill_items(master_id):
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select * from product,bill where product.product_id = bill.product_id and  bill.master_id = '" + master_id + "' ")
        total = db.selectOne(
            "select sum(quantity*price) as t from product,bill where product.product_id = bill.product_id and  bill.master_id = '" + master_id + "'")
        return render_template("shop/view_bill_items.html", data=data, total=total['t'])
    return redirect('/')


# ---------------------------------------------- shop section End ----------------------------------------------


# ---------------------------------------------- Android section ----------------------------------------------

# and means android

# ********************************************** Android user registration *********************************************

@app.route('/and_user_register', methods=['post'])
def and_user_register():
    and_register_user_name = request.form['andUserName']
    and_register_user_place = request.form['andUserPlace']
    and_register_user_pin = request.form['andUserPIN']
    and_register_user_mail = request.form['andUserMail']
    and_register_user_phone = request.form['andUserPhone']
    and_register_user_password = request.form['andUserPassword']
    and_register_gender = request.form['andUserGender']
    and_register_image = request.files['pic']

    date = datetime.datetime.now().strftime("%y%m%d-%H%M%S ")
    and_register_image.save(r"E:\QR shopping\python\static\images\\" + date + '.jpg')
    image_path = "/static/images/" + date + '.jpg'

    db = Database()
    q = db.insert(
        "insert into login VALUES ('','" + and_register_user_mail + "','" + and_register_user_password + "','user')")

    db.insert("insert into `user` VALUES ('" + str(
        q) + "','" + and_register_user_name + "','" + and_register_user_place + "','" + and_register_user_pin + "','" + and_register_user_mail + "','" + and_register_gender + "','" + and_register_user_phone + "','" + str(
        image_path) + "')")
    return jsonify(status="ok")


# ********************************************** Android user login *********************************************

@app.route('/and_login', methods=['post'])
def and_login():
    and_user_email = request.form['u']
    and_user_password = request.form['p']
    db = Database()
    query = db.selectOne(
        "select * from login where user_name = '" + and_user_email + "'and password = '" + and_user_password + "'")
    if query is not None:
        # if query['user_type'] == 'user'
        return jsonify(status="OK", lid=query['login_id'], type=query['user_type'])
    else:
        return jsonify(status="NO")
        # else:
        #     return jsonify(status="Invalid")
        # return jsonify(status='OK')


# ********************************************** Android user view profile *********************************************

@app.route('/and_view_profile', methods=['post'])
def and_view_profile():
    user_id = request.form['userID']
    db = Database()
    res = db.selectOne("select * from `user` where user_id ='" + user_id + "'")

    return jsonify(status="ok", data=res)


# ********************************************** Android user view shop *********************************************

@app.route('/and_view_shop', methods=['post'])
def and_view_shop():
    db = Database()
    data = db.select("select * from shop,login where shop.shop_id = login.login_id and login.user_type = 'shop'")
    return jsonify(status="ok", data=data)


# ********************************************** Android user view product *********************************************

@app.route('/and_view_product', methods=['post'])
def and_view_product():
    and_shop_id = request.form['shopID']
    db = Database()
    data = db.select(
        "select * from product,stock where product.product_id = stock.product_id and  product.shop_id ='" + and_shop_id + "' ")
    return jsonify(status="ok", data=data)


# ********************************************** Android user view offer *********************************************

@app.route('/and_view_offer', methods=['post'])
def and_view_offer():
    # Discount = ActualPrice - (ActualPrice * Discount_Rate / 100)
    product_id = request.form['productID']
    # sId = request.form['sId']
    db = Database()
    data = db.select(
        "select product.price-(product.price*offer.offer/100) as total,offer.*,shop.*,product.* from product,offer,shop where offer.product_id=product.product_id and  product.product_id = '" + product_id + "' and shop.shop_id = product.shop_id")
    # data= db.select("select * from offer where offer.product_id = '"+product_id+"'")
    if len(data) > 0:
        return jsonify(status="ok", data=data)
    else:
        return jsonify(status="no")


# ********************************************** Android user send complaint *********************************************

@app.route('/and_sendComplaint', methods=['post'])
def and_sendComplaint():
    complaint = request.form['comp']
    uid = request.form['id']
    db = Database()
    db.insert("insert into complaint values('','user','" + uid + "','" + complaint + "',curdate(),'pending','pending')")
    return jsonify(status="ok")


# ********************************************** Android user view reply *********************************************

@app.route('/and_view_reply', methods=['post'])
def and_view_reply():
    user_id = request.form['id']
    db = Database()
    data = db.select("select * from complaint where user_id = '" + user_id + "' ")
    return jsonify(status="ok", data=data)


# ********************************************* Android user send feedback *********************************************

@app.route('/and_send_feedback', methods=['post'])
def and_send_feedback():
    feedback = request.form['fed']
    uid = request.form['id']
    db = Database()
    db.insert("insert into feedback values('','" + uid + "','user',curdate(),'" + feedback + "')")
    return jsonify(status="ok")


@app.route('/and_add_rating', methods=['post'])
def and_add_rating():
    rate = request.form['rate']
    uid = request.form['id']
    shopID = request.form['shopID']
    db = Database()
    res = db.selectOne("select * from rating where shop_id = '" + shopID + "' and user_id = '" + uid + "'")
    if res is not None:
        db = Database()
        db.update(
            "update rating set rating = '" + rate + "' where  shop_id = '" + shopID + "' and user_id = '" + uid + "'")
        return jsonify(status="updated")
    else:
        db = Database()
        db.insert("insert into rating VALUES ('',curdate(),'" + uid + "','" + rate + "','" + shopID + "')")
        return jsonify(status="ok")


# ********************************************** Android user view quantity *******************************************
#
# @app.route('/and_quantity', methods=['post'])
# def and_quantity():
#     productID = request.form['productID']
#     product_quantity = request.form['qua']
#     uid = request.form['id']
#     product_price = request.form['productPrice']
#     shop_ID = request.form['shopID']
#     db = Database()
#     res2 = db.selectOne("select * from bill,bill_master where bill.master_id = bill_master.master_id and bill.product_id = '"+productID+"' and bill_master.user_id = '"+uid+"' and bill_master.status = 'add to cart'")
#
#     res1 = db.selectOne(
#         "select * from stock,product where stock.product_id = product.product_id and product.product_id = '" + productID + "'")
#     if product_quantity >= str(res1['quantity']):
#         return jsonify(status="greater")
#     elif res2 is not None:
#         db = Database()
#         db.update("update bill set quantity = '"+product_quantity+"' where product_id = '"+productID+"'")
#         return jsonify(status="update")
#
#
#
#

#####################

@app.route('/and_quantity', methods=['post'])
def and_quantity():
    productID = request.form['productID']  # product id
    product_quantity = request.form['qua']  # product quantity
    uid = request.form['id']  # user id
    product_price = request.form['productPrice']  # product price
    shop_ID = request.form['shopID']  # shop id

    # database names are shop,product,bill,bill_master,user,stock,login,offer,complaint,feedback,rating
    # bill table contain bill_id,master_id,product_id and quantity
    # bill_master table contain master_id,shop_id,user_id, amount, date and status
    # stock table contain stock_id,product_id and quantity
    # product table contain product_id,name,price,details, shop_id and image
    # shop table contain shop_id,name,place,pincode,mail,phone and image
    # user table contain user_id,name,place,pincode,mail,gender, phone and image



    db = Database()
    res1 = db.selectOne(
        "select * from stock,product where stock.product_id = product.product_id and product.product_id = '" + productID + "'")
    if product_quantity > str(res1['quantity']):
        print(product_quantity)
        return jsonify(status="greater")
    else:

        res2 = db.selectOne("select * from bill where product_id='" + productID + "'")
        if res2 is not None:
            db = Database()
            db.update(
                "update bill set quantity=quantity + '" + product_quantity + "' where product_id='" + productID + "'")
            return jsonify(status="productqty")
        else:
            res0 = db.selectOne(
                "select * from bill_master where user_id = '" + uid + "' and bill_master.shop_id='" + shop_ID + "' ")
            if res0 is not None:
                db = Database()
                db.insert("insert into bill VALUES ('','" + str(
                    res0['master_id']) + "','" + productID + "','" + product_quantity + "')")
                db.update("update bill set quantity='" + product_quantity + "' where product_id='" + productID + "'")
                return jsonify(status="cart")
            else:
                db = Database()
                res = db.insert(
                    "insert into bill_master VALUES ('','" + shop_ID + "','" + uid + "','" + product_price + "',curdate(),'add to cart')")
                db.insert(
                    "insert into bill VALUES ('','" + str(res) + "','" + productID + "','" + product_quantity + "')")
                return jsonify(status="ok")



                # res0=db.selectOne("select * from bill_master where shop_id='"+shop_ID+"' and user_id='"+uid+"'")
                # if res0 is not None:
                #     db = Database()
                #     db.insert("insert into bill VALUES ('','"+str(res0['master_id'])+"','"+productID+"','"+product_quantity+"')")
                #     return jsonify(status="cart")
                # else:
                #     db = Database()
                #     res = db.insert("insert into bill_master VALUES ('','" + shop_ID + "','" + uid + "','" + product_price + "',curdate(),'add to cart')")
                #     db.insert("insert into bill VALUES ('','" + str(res) + "','" + productID + "','" + product_quantity + "')")
                #     return jsonify(status="ok")#


# ******************************************** Android user view product cart ******************************************

@app.route('/and_view_product_cart', methods=['post'])
def and_view_product_cart():
    uid = request.form['id']
    shopId = request.form['shopId']
    db = Database()
    res = db.select(
        "select * from bill,bill_master,product where bill.master_id=bill_master.master_id and bill_master.user_id='" + uid + "' and bill.product_id = product.product_id and product.shop_id='" + shopId + "'")
    if len(res) > 0:
        return jsonify(status="ok", data=res)
    else:
        return jsonify(status="no")


# ****************************************** Android user delete cart product ***************************************

@app.route('/and_product_cart_delete', methods=['post'])
def and_product_cart_delete():
    bill_ID = request.form['bill']
    db = Database()
    db.delete("delete from bill where bill_id = '" + bill_ID + "' ")
    return jsonify(status="ok")


@app.route('/and_view_bill', methods=['post'])
def and_view_bill():
    uid = request.form['id']
    db = Database()
    data = db.select(
        "select * from bill_master, bill,shop where bill.master_id=bill_master.master_id and bill_master.shop_id=shop.shop_id and bill_master.user_id='" + uid + "' ")
    # data=db.select("select sum(product.price*bill.quantity) as total,bill.*,bill_master.*,product.* from bill,bill_master,product where bill.master_id = bill_master.master_id and product.product_id = bill.product_id and bill_master.user_id = '"+uid+"'")

    return jsonify(status="ok", data=data)


@app.route('/and_view_product_bill', methods=['post'])
def and_view_product_bill():
    billID = request.form['billID']
    uID = request.form['id']
    db = Database()
    data = db.select(
        "select * from bill,bill_master,product where bill.product_id=product.product_id and bill.bill_id='" + billID + "' and bill.master_id=bill_master.master_id and bill_master.user_id='" + uID + "'")
    return jsonify(status="ok", data=data)


@app.route('/and_single_buy_quantity', methods=['post'])
def and_single_buy_quantity():
    productID = request.form['productID']  # product id
    product_quantity = request.form['qua']  # product quantity
    uid = request.form['id']  # user id
    product_price = request.form['productPrice']  # product price
    shop_ID = request.form['shopID']  # shop id
    db = Database()
    res0 = db.selectOne("select * from bill_master where user_id = '" + uid + "' and shop_id = '" + shop_ID + "' and bill_master.status='book' ")
    total_product_quantity = int(product_quantity)
    if res0 is not None:
        res1 = db.selectOne(
            "select * from stock,product where stock.product_id=product.product_id and product.product_id='" + productID + "'")

        if res1 is not None:
            if total_product_quantity > int(res1['quantity']):
                print(product_quantity)
                return jsonify(status="greater")
            else:
                db = Database()
                db.insert("insert into bill VALUES ('','" + str(
                    res0['master_id']) + "','" + productID + "','" + product_quantity + "')")
                db.update(
                    "update bill_master set amount = '" + product_price + "' * '" + product_quantity + "' where user_id = '" + uid + "' and shop_id ='" + shop_ID + "'")
                db.update(
                    "update stock set quantity=quantity - '" + product_quantity + "' where product_id='" + productID + "'")
                return jsonify(status="added")
        else:
            return jsonify(status="greater")
    else:
        db = Database()
        res = db.insert(
            "insert into bill_master VALUES ('','" + shop_ID + "','" + uid + "','" + product_price + "',curdate(),'book')")
        db.insert("insert into bill VALUES ('','" + str(res) + "','" + productID + "','" + product_quantity + "')")
        db.update(
            "update stock set quantity=quantity - '" + product_quantity + "' where product_id='" + productID + "'")
        db.update(
            "update bill_master set amount = '" + product_price + "' * '" + product_quantity + "' where user_id = '" + uid + "' and shop_id ='" + shop_ID + "'")
        return jsonify(status="ok")


# and_view_shop_in_cart
# user_id = request.form['id']

@app.route('/and_view_shop_in_cart', methods=['post'])
def and_view_shop_in_cart():
    uid = request.form['id']
    db = Database()
    data = db.select(
        "select * from bill_master,shop where bill_master.shop_id=shop.shop_id and bill_master.user_id='" + uid + "' and bill_master.status='book'")
    return jsonify(status="ok", data=data)


@app.route('/and_payment', methods=['post'])
def and_payment():
    bank_name = request.form['bankName']
    bank_account_no = request.form['accountNo']
    bank_ifsc_code = request.form['IFSCode']
    total_amount = request.form['totalAmount']
    uID = request.form['id']
    db = Database()
    res = db.selectOne(
        "select * from payment where bank_name = '" + bank_name + "' and  ifsc_code = '" + bank_ifsc_code + "' and account_no='" + bank_account_no + "' and  amount ='" + total_amount + "' and  holder_id='" + uID + "'")
    if res is not None:
        if total_amount > int(res['amount']):
            return jsonify(status="greater")
        else:
            db.update(
                "update payment set amountamount - '" + total_amount + "' where account_no='" + bank_account_no + "' and ifsc_code='" + bank_ifsc_code + "' and holder_id='" + uID + "'")
            db.update("update bill_master set status='paid' where user_id='" + uID + "'")
            return jsonify(status="ok")
    else:
        return jsonify(status="greater")

        # return jsonify(status="ok")


@app.route('/and_product_price',methods=['post'])
def and_product_price():
    uid = request.form['id']
    shopID = request.form['shopID']
    productID = request.form['productID']
    db = Database()
    res=db.selectOne("select * from bill_master,bill where  bill_master.master_id=bill.master_id and bill.product_id='"+productID+"' and bill_master.shop_id='"+shopID+"' and  user_id = '"+uid+"'")
    return jsonify(status="ok",data=res)




if __name__ == '__main__':
    app.run(debug=True, port=4000, host="0.0.0.0")
