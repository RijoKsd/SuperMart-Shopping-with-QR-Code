from flask import Flask, render_template, request, redirect
from DBConnection import Database

app = Flask(__name__)


# login page
@app.route('/', methods=['get', 'post'])
def login():
    if request.method == "POST":
        username = request.form['username']
        password = request.form['password']
        db = Database()
        query = db.selectOne(
            "select * from login where user_name = '" + username + "'and password = '" + password + "'")
        if query is not None:
            if query['user_type'] == 'admin':
                return redirect('/admin_home')
            else:
                return '<script>alert("invalid credentials");window.location = "/"</script>'
        else:
            return '<script>alert("invalid username or password");window.location = "/"</script>'

    else:
        return render_template("login.html")


# approve or verify shop
@app.route('/verify_shop')
def verify_shop():
    db = Database()
    data = db.select("select * from shop,login where shop.shop_id = login.login_id and login.user_type = 'pending'")
    return render_template("admin/verify_shop.html", data=data)


@app.route('/approve/<shop_id>')
def approve(shop_id):
    db = Database()
    db.update("update login set user_type = 'shop' where login_id = '" + str(shop_id) + "'")
    return redirect('/verify_shop')


@app.route('/reject/<shop_id>')
def reject(shop_id):
    db = Database()
    db.delete("delete from login where login_id = '" + str(shop_id) + "'")
    db.delete("delete from shop where shop_id = '" + str(shop_id) + "'")
    return redirect('/verify_shop')


@app.route('/view_approved_shop')
def view_approved_shop():
    db = Database()
    data = db.select(
        "select * from shop,login where shop.shop_id = login.login_id and (login.user_type = 'shop' or login.user_type='block')")
    return render_template("admin/view_approved_shop.html", data=data)


@app.route('/block/<shop_id>')
def block(shop_id):
    db = Database()
    db.update("update login set user_type = 'block' where login_id = '" + str(shop_id) + "'")
    # db.delete("delete from login where login_id = '" + str(shop_id) + "'")
    # Change block to blocked in web page

    return redirect('/view_approved_shop')


@app.route('/unblock/<shop_id>')
def unblock(shop_id):
    db = Database()
    db.update("update login set user_type = 'shop' where login_id = '" + str(shop_id) + "'")
    return redirect('/view_approved_shop')


@app.route('/view_feedback')
def view_feedback():
    db = Database()
    data = db.select("select * from feedback,user where user.user_id = feedback.sender_id ")
    return render_template("admin/view_feedback.html", data=data)


# filter using select option user and shop
@app.route('/view_complaint_send_reply', methods=['get', 'post'])
def view_complaint_send_reply():
    if request.method == "POST":
        select_option = request.form['select']
        if select_option == "user":
            db = Database()
            data = db.select("select * from complaint,user where complaint.user_id = user.user_id")
            return render_template("admin/view_complaint_send_reply.html", data=data)
        else:
            db = Database()
            data = db.select("select * from complaint,shop where complaint.user_id = shop.shop_id")
            return render_template("admin/view_complaint_send_reply.html", data=data)
    else:
        return render_template("admin/view_complaint_send_reply.html")


@app.route('/send_reply/<user_id>', methods=['post', 'get'])
def send_reply(user_id):
    if request.method == "POST":
        return "hao"


@app.route('/view_rating')
def view_rating():
    db = Database()
    data = db.select(
        "select rating.rating_id, rating.rating,user.user_id,shop.shop_id,user.name,shop.name,rating.date from rating,user,shop where shop.shop_id = rating.shop_id and user.user_id = rating.user_id")
    return render_template("admin/view_rating.html", data=data)


# This page load after the admin entered correct username and password
@app.route('/admin_home')
def admin_home():
    return render_template("admin/admin_home.html")


@app.route('/reply/<complaint_id>',methods = ['get','post'])
def reply(complaint_id):
    if request.method == "POST":
        reply = request.form['reply']
        db = Database()
        data = db.update("update complaint set reply = '" + reply + "' ,reply_date = curdate() where complaint_id = '"+complaint_id+"'")
        return '<script>alert(" Reply send successfully");window.location = "/view_complaint_send_reply"</script>'
    else:
        return render_template('admin/reply.html')


# admin section finished

# ----------------------------------------------













if __name__ == '__main__':
    app.run(debug=True, port=4000)
