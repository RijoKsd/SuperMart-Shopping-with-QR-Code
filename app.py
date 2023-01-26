from flask import Flask, render_template, request, redirect, session
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
            "select * from bill_master,user,product,bill where bill_master.user_id = user.user_id and bill_master.master_id = bill.master_id and product.product_id = bill.product_id  and bill_master.shop_id = '" + str(
                session['lid']) + "'")
        return render_template("shop/view_bill.html", data=data)
    return redirect('/')


# ********************************************** View bill items **********************************************

@app.route('/view_bill_items/<master_id>')
def view_bill_items(master_id):
    if session['lin'] == "1":
        db = Database()
        data = db.select(
            "select * from product,bill where product.product_id = bill.product_id and  bill.master_id = '" + master_id + "'")
        total = db.selectOne(
            "select sum(quantity*price) as t from product,bill where product.product_id = bill.product_id and  bill.master_id = '" + master_id + "'")
        return render_template("shop/view_bill_items.html", data=data, total=total['t'])
    return redirect('/')

# ---------------------------------------------- shop section End ----------------------------------------------


if __name__ == '__main__':
    app.run(debug=True, port=4000)
