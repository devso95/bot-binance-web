<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <p>
        <span style="color: red; ">${errorMessage}</span>
    </p>
    <form action="/login" method="POST">
        <fieldset class="form-group">
            <label>UserName</label> <input name="username" type="text"
                                           class="form-control"/>
        </fieldset>
        <fieldset class="form-group">
            <label>Password</label> <input name="password" type="password"
                                           class="form-control"/>
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
    </form>
    <div class="container">
        <a href="/register" style=""><u><span style="color: #0000FF; ">Register</span></u></a>
    </div>
</div>