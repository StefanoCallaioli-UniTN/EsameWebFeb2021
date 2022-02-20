function createSudokuList (times){
    
    var sudokuList = document.getElementById('list');       

    var i;
    var listItem;
    for (i=1;i<=times;i++){
        listItem = document.createElement('li');
        link = document.createElement('a');
        link.setAttribute("href","./CreateSudoku?id="+i);
        link.appendChild(document.createTextNode("Sudoku n."+i));
        
        listItem.appendChild(link);
        sudokuList.appendChild(listItem);
   }   
   return sudokuList;
}

function populateTable(table, rows, cells) {
    var sudokuTable = document.getElementById('table'); 
    for (var i = 0; i < rows; i++) {
        var row = document.createElement('tr');
        for (var j = 0; j < cells; j++) {
            row.appendChild(document.createElement('td'));
            var inputField = document.createElement("INPUT");
            inputField.setAttribute("type", "text");
            inputField.setAttribute("maxlength", "1");
            inputField.setAttribute("onkeypress", "return isNumberKey(event)");
            row.cells[j].appendChild(inputField);
            row.cells[j].firstChild.id=((i+1)*10 + (j+1));
            var ry= ~~(i/3);
            var rx= ~~(j/3);
            if ((rx+ry)%2 === 0){
                inputField.setAttribute("class", "region1");
            } else{
                inputField.setAttribute("class", "region2");
            }
        }
        sudokuTable.appendChild(row);
    }
    return table;
}

function initialize(coords,values){
    var coordsArray = JSON.parse(coords);
    var valuesArray = JSON.parse(values);
    
    var i;
    for(i=0;i<coordsArray.length;i++){
        var cell = document.getElementById(coordsArray[i]);
        cell.setAttribute("value",valuesArray[i]);
        cell.setAttribute("class",cell.getAttribute("class")+" fixed");
        cell.setAttribute("readonly",true);
    }
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function verifica(id){
    var riga = document.getElementById('riga').value;
    var colonna = document.getElementById('colonna').value;
    var risposta = document.getElementById(""+ riga + colonna).value;
    
    var url = "verifica?id="+id+"&righe="+riga+"&colonna="+colonna+"&risposta="+risposta;
    
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url + ((/\?/).test(url) ? "&" : "?") + (new Date()).getTime(), true);
    
    xhttp.onreadystatechange=function() {
        var done = 4, ok = 200;
        if(this.readyState === done && this.status === ok) {
            if (this.response=="true"){
                alert("La risposta "+ risposta + " alle coordinate " + riga + ":" + colonna + " è corretta!");
            }else{
                alert("La risposta "+ risposta + " alle coordinate " + riga + ":" + colonna + " è sbagliata!");
            }
        }
    };
    xhttp.send();
    
    //alert(riga + " " + colonna);
}
