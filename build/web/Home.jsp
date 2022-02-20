<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="./javascript/scripts.js"></script>
        <title>Home</title>
    </head>
    <body>
        <h1>Sudoku by Stefano Callaioli!</h1>
        <ul id="list"></ul>
        <% int c = (int)session.getAttribute("numeroSudoku"); %>
        <script>createSudokuList(<%= c %>);</script>
    </body>
</html>
