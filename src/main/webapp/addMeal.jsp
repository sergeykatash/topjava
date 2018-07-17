<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals?action=save&id=${meal.id}">
    <dl>
        <dt>DataTime</dt>
        <dd> <javatime:format value="${meal.dateTime}" style="MS" /></dd>
    </dl>
    <dl>
        <dt>Description: </dt>
        <dd><input type="text" name="newDescr" value="${meal.description}" placeholder="${meal.description}" /></dd>
    </dl>
    <dl>
        <dt>Calories: </dt>
        <dd><input type="number" name="newCalory" value="${meal.calories}" placeholder="${meal.calories}" /></dd>
    </dl>
    <button type="submit">Save</button>
</form>
</body>
</html>
