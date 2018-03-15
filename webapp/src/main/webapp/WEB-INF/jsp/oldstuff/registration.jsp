<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Data</title>
</head>
<style>
    div.ex {
        text-align: right;
        width: 300px;
        padding: 10px;
        margin: 0px
    }
</style>
<center>
    <body>
    <h1> Sign Up</h1>
    <div class="ex">
        <form action="RegistrationController" method="post">

            <table border="1" width="30%" cellpadding="5">
                <thead>
                </thead>
                <tbody>
                <tr>
                    <td>*First Name</td>
                    <td><input type="text" name="fname" value=""/></td>
                </tr>
                <tr>
                    <td>*Last Name</td>
                    <td><input type="text" name="lname" value=""/></td>
                </tr>
                <tr>
                    <td>Middle Name</td>
                    <td><input type="text" name="mname" value=""/></td>
                </tr>
                <tr>
                    <td>*Email</td>
                    <td><input type="text" name="email" value=""/></td>
                </tr>
                <tr>
                    <td>*Password</td>
                    <td><input type="password" name="pass" value=""/></td>
                </tr>
                <tr>
                    <td>Male</td>
                    <td><input type="radio" name="gender" value="MALE" checked></td>
                </tr>
                <tr>
                    <td>Female</td>
                    <td><input type="radio" name="gender" value="FEMALE" checked></td>
                </tr>
                <tr>
                    <td><label for="date">*Birth date</label></td>
                    <td><input id="date" type="date" name="date"></td>
                </tr>
                <tr>
                    <td>Home address</td>
                    <td><input type="text" name="homeAddress" value=""/></td>
                </tr>
                <tr>
                    <td>Work address</td>
                    <td><input type="text" name="workAddress" value=""/></td>
                </tr>
                <tr>
                    <td>ICQ</td>
                    <td><input type="number" name="icq" value=""/></td>
                </tr>
                <tr>
                    <td>Skype</td>
                    <td><input type="text" name="skype" value=""/></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type='tel' name="phone" pattern='[\+]\d{2}[\(]\d{2}[\)]\d{4}[\-]\d{4}'
                               title='Phone Number (Format: +99(99)9999-9999)'></td>

                </tr>
                <tr>
                    <td>Additional information</td>
                    <td><textarea name="additionalInfo" cols="19" rows="5"></textarea></td>
                </tr>
                <%--<tr>
                    <td>Photo</td>
                    <td><input type="file" name="image"></td>
                </tr>--%>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                    <%--<td><input type="reset" value="Reset" /></td>--%>
                </tr>
                <tr>
                    <td colspan="2">Already registered!! <a href="/webapp">Login Here</a></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    </body>
</center>
</html>