<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <form:form modelAttribute="strategy" method="post" action="/update-strategy">
        <form:hidden path="id"/>
        <fieldset class="form-group">
            <form:label path="coin">Coin</form:label>
            <br/>
            <form:input path="coin" type="text" class="form-control"
                        required="required"/>
            <form:errors path="coin" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="stopLoss">Stoploss</form:label>
            <br/>
            <form:input path="stopLoss" type="number" class="form-control"
                        required="required" step="1"/>
            <form:errors path="stopLoss" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="liquidation">Liquidation</form:label>
            <br/>
            <form:input path="liquidation" type="number" class="form-control"
                        required="required" step="1"/>
            <form:errors path="liquidation" cssClass="text-warning"/>
        </fieldset>


        <fieldset class="form-group">
            <form:label path="target">Target</form:label>
            <br/>
            <form:input path="target" type="number" step="1" class="form-control"
                        required="required"/>
            <form:errors path="target" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="crossRate">CrossRate</form:label>
            <br/>
            <form:input path="crossRate" type="number" class="form-control"
                        required="required" step='0.1' min="0" max="1"/>
            <form:errors path="crossRate" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="apiKey">APIKey</form:label>
            <br/>
            <form:input path="apiKey" type="text" class="form-control"/>
            <form:errors path="apiKey" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="secretKey">SecretKey</form:label>
            <br/>
            <form:input path="secretKey" class="form-control"/>
            <form:errors path="secretKey" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="state">State (ACTIVE, CLOSE)</form:label>
            <br/>
            <form:input path="state" type="text" class="form-control"
                        required="required"/>
            <form:errors path="state" cssClass="text-warning"/>
        </fieldset>

        <button type="submit" class="btn btn-success">Update</button>
    </form:form>
</div>


<%@ include file="common/footer.jspf" %>