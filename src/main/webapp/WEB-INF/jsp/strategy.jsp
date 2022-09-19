<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <style>
        td {
            border: 1px solid black;
            padding: 10px 10px 10px 15px;
            align-content: center;
        }
        table {
            border-collapse: collapse;
        }
        th {
            padding-right: 15px;
            padding-left: 15px;
            padding-bottom: 10px;
        }

    </style>
    <table class="table table-striped">
        <caption><h2>Your strategies are</h2></caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Coin</th>
            <th>Stoploss</th>
            <th><span style="color: #FF0000; ">Liquidation</span></th>
            <th><span style="color: #26915a; ">Target</span></th>
            <th>State</th>
            <th>CrossRate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${strategies}" var="strategy">
            <tr>
                <td>${strategy.id}</td>
                <td>${strategy.coin}</td>
                <td>${strategy.stopLoss}</td>
                <td><span style="color: #FF0000; ">${strategy.liquidation}</span></td>
                <td><span style="color: #26915a; ">${strategy.target}</span></td>
                <td>${strategy.state}</td>
                <td>${strategy.crossRate}</td>
                <td><a type="button" class="btn btn-success"
                       padding:="10px"
                       href="/update-strategy?id=${strategy.id}"> Update </a></td>
                <td><a type="button" class="btn btn-warning"
                       padding:="10px"
                       href="/delete-strategy?id=${strategy.id}"> Delete </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div>
        <a class="button" href="/add-strategy">Add a Strategy</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>