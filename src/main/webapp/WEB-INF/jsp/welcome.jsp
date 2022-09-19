<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<%@ page import="deso.future_bot.futurebotweb.Formatter" %>
<%
    response.setIntHeader("Refresh", 60);
%>
<div class="container">
    <h1>Welcome ${name}!!</h1>
    <h3>Asset <span style="color: #0000FF; ">${Formatter.formatDecimal(summary.currentValue, 0)}</span> / Position <span style="color: #FF0000; ">${Formatter.formatDecimal(summary.currentPosition, 0)}</span> USDT</h3>
    <h3>Pnl <span style="color: #26915a; ">${Formatter.formatDecimal(summary.pnl, 0)}</span></h3>
    <div>${error}</div>
    <div><a href="/strategy">Click here</a> to manage your strategies.</div>
</div>
<br/>
<%@ include file="common/footer.jspf" %>