
<!doctype html>
<html lang="en">
    <head>
        <title>Sign Up</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="account_css/style.css">

    </head>
    <body class="img js-fullheight" style="background-image: url(account_images/bg.jpg);">
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">My Little Garden</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <div class="login-wrap p-0">
                            <h3 class="mb-4 text-center">Have an account?</h3>
                            <form action="UserController" class="signin-form" method="post">
                                 <center> <span style="color:red">${error}</span></center>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="username" placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" name="password" class="form-control" placeholder="Password" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="form-control btn btn-primary submit px-3" name="action" value="signIn">Sign In</button>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="w-50">

                                    </div>
                                    <div class="w-50 text-md-right">
                                        <a href="#" style="color: #fff">Forgot Password</a>
                                    </div>
                                </div>
                            </form>
                            <p class="w-100 text-center">&mdash; Or Sign In With &mdash;</p>
                            <div class="social d-flex text-center">

                                <a href="registerAccount.jsp" class="px-2 py-2 ml-md-1 rounded"><span class="ion-logo-twitter mr-2"></span>Register</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="account_js/jquery.min.js"></script>
        <script src="account_js/popper.js"></script>
        <script src="account_js/bootstrap.min.js"></script>
        <script src="account_js/main.js"></script>

    </body>
</html>

