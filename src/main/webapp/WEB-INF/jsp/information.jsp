<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<%@ page import="deso.future_bot.futurebotweb.Formatter" %>
<%
    response.setIntHeader("Refresh", 15);
%>
<style>
    td {
        border: 1px solid black;
        padding: 10px 10px 10px 15px;
        align-content: center;
    }

    th {
        padding-right: 15px;
        padding-left: 15px;
        padding-bottom: 10px;
    }

    table {
        border-collapse: collapse;
    }
</style>
<div class="container">
    <table class="table table-striped">
        <caption><h2>Trading information</h2></caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Quantity</th>
            <th>CurrentPosition</th>
            <th>CurrentValue</th>
            <th>CurrentLevel</th>
            <th>AverageEntry</th>
            <th>MarkPrice</th>
            <th><span style="color: #FF0000; ">Liquidation</span></th>
            <th><span style="color: #26915a; ">PNL</span></th>
            <th>OpenOrders</th>
            <th>WaitingOrders</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${informations}" var="information">
            <tr>
                <td>${information.id}</td>
                <td>${Formatter.formatDecimal(information.quantity)} ${information.pair}</td>
                <td>${Formatter.formatDecimal(information.currentPosition)}</td>
                <td>${Formatter.formatDecimal(information.currentValue)}</td>
                <td>x${Formatter.formatDecimal(information.currentLevel)}</td>
                <td>${Formatter.formatDecimal(information.averageEntry,1)}</td>
                <td>${Formatter.formatDecimal(information.markPrice,1)}</td>
                <td><span style="color: #FF0000; ">${Formatter.formatDecimal(information.liquidation,1)}</span></td>
                <td>${Formatter.formatDecimal(information.pnl,1)}</td>
                <td>${information.openOrderCount}</td>
                <td>${information.waitingOrderCount}</td>
            </tr>
        </c:forEach>
        <tr style="font-weight:bold">
            <td>Total</td>
            <td></td>
            <td>${Formatter.formatDecimal(summary.currentPosition)}</td>
            <td>${Formatter.formatDecimal(summary.currentValue)}</td>
            <td>x${Formatter.formatDecimal(summary.currentLevel)}</td>
            <td></td>
            <td></td>
            <td></td>
            <td><span style="color: #26915a; ">${Formatter.formatDecimal(summary.pnl)}</span></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    <br/>
    <div>
        <a class="button" href="/strategy">Back</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>