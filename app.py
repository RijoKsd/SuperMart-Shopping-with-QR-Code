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
    return render_template("admin/verify_shop.html")


@app.route('/view_approved_shop')
def view_approved_shop():
    db = Database()
    data = db.select("select * from shop")
    return render_template("admin/view_approved_shop.html", data=data)


@app.route('/block_shop')
def block_shop():
    db = Database()
    return render_template("admin/block_shop.html")


@app.route('/view_feedback')
def view_feedback():
    db = Database()
    data = db.select("select * from feedback")
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


@app.route('/view_rating')
def view_rating():
    db = Database()
    data = db.select("select * from rating")
    return render_template("admin/view_rating.html", data=data)


# This page load after the admin entered correct username and password
@app.route('/admin_home')
def admin_home():
    return render_template("admin/admin_home.html")


# admin section finished

# ----------------------------------------------


if __name__ == '__main__':
    app.run(debug=True)
