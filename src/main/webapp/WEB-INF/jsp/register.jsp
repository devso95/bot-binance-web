<html>
<head>
    <script>
        function onSubmit() {
            const pass1 = document.getElementById("pass").value;
            const pass2 = document.getElementById("rpass").value;
            if (pass1 !== pass2) {
                document.getElementById("pass").style.borderColor = "#E34234";
                document.getElementById("rpass").style.borderColor = "#E34234";
            } else {
                document.getElementById("submit").submit();
            }
        }
    </script>
</head>
<body>
<div class="form_wrapper">
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
    <div class="form_container">
        <div class="title_container">
            <h2>Registration Binance Future Bot</h2>
        </div>
        <div class="row clearfix">
            <div class="">
                <form modelAttribute="user" id="submit" method="post" action="/users">
                    <div class="input_field"><span><i aria-hidden="true" class="fa fa-envelope"></i></span>
                        <input path="login" type="text" pattern="[a-zA-Z0-9._]{6,20}"
                               name="login" placeholder="Username" required/>
                    </div>
                    <div class="input_field"><span><i aria-hidden="true" class="fa fa-lock"></i></span>
                        <input path="password" type="password" name="password" id="pass" placeholder="Password"
                               required/>
                    </div>
                    <div class="input_field"><span><i aria-hidden="true" class="fa fa-lock"></i></span>
                        <input type="password" name="rpassword" id="rpass" placeholder="Re-type Password" required/>
                    </div>
                    <div class="row clearfix">
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-user"></i></span>
                                <input type="text" name="firstName" placeholder="First Name"/>
                            </div>
                        </div>
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-user"></i></span>
                                <input type="text" name="lastName" placeholder="Last Name" required/>
                            </div>
                        </div>
                    </div>
                    <div class="input_field radio_option">
                        <input type="radio" name="gender" id="rd1" path="gender" value="MALE">
                        <label for="rd1">Male</label>
                        <input type="radio" name="gender" id="rd2" path="gender" value="FEMALE">
                        <label for="rd2">Female</label>
                    </div>
                    <div class="input_field checkbox_option">
                        <input type="checkbox" id="cb1">
                        <label for="cb1">I agree with terms and conditions</label>
                    </div>
                    <input class="button" type="button" value="Register" onclick="onSubmit()"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>