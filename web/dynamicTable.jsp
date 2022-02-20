<%-- 
    Document   : dynamicTable
    Created on : 16-giu-2021, 11.25.49
    Author     : stefa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% int c = (int)session.getAttribute("numeroSudoku"); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css">
        <script type="text/javascript" src="./javascript/scripts.js"></script>
        <title>Dynamic Table</title>
    </head>
    <body>
        <table id="table"></table>
        <form id="form">
            <label for="riga">Riga:</label>
            <input type="text" name="riga" value="" id="riga" maxlength="1" onkeypress="return isNumberKey(event)">
            <label for="colonna">Colonna:</label>
            <input type="text" name="colonna" value="" id="colonna" maxlength="1" onkeypress="return isNumberKey(event)">
            <input type="button" value="verifica" onclick="verifica((<%= c %>))">
        </form>
        <script>
            populateTable("table",9,9);
            initialize("<%= request.getAttribute("fixedCellsCord") %>","<%= request.getAttribute("fixedCellsValue") %>");
        </script>
    </body>
</html>
