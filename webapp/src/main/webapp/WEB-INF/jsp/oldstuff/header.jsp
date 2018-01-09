<div>
    <div style="float: left;">
        <form action="SearchController" method="post">
            <tr>
                <td>Groups</td>
                <td><input type="radio" name="gender" value="GROUPS" checked></td>
            </tr>
            <tr>
                <td>Friends</td>
                <td><input type="radio" name="gender" value="FRIENDS" checked></td>
            </tr>
            <tr>
                <td><input type="text" name="search" placeholder="Search.."></td>
            </tr>

        </form>
    </div>
    <div style="float: right;">
        <form action="/webapp" method="get">
            <%
                String userEmail = (String) request.getSession().getAttribute("accEmail");
                if (!userEmail.isEmpty()) {
            %>
            <tr>
                <td>User:</td>
                <td><%=userEmail%>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td><input type="submit" value="Logout"/></td>
            </tr>
        </form>
    </div>
    <br>
    <br>
</div>