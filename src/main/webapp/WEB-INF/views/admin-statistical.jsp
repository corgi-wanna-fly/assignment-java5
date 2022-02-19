<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
<body>

<div class="container mt-3" style="text-align: center">
    <div id="piechart"></div>
</div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
    // Load google charts
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    let o_json = JSON.stringify(${json});

    o_json = o_json.substr(1, o_json.length-2);

    const array = o_json.split(",");
    const array0 = array[0].split(":");
    const array1 = array[1].split(":");
    const array2 = array[2].split(":");
    const array3 = array[3].split(":");

    // Draw the chart and set the chart values
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Orders Quantity'],
            [array0[0], parseInt(array0[1])],
            [array1[0], parseInt(array1[1])],
            [array2[0], parseInt(array2[1])],
            [array3[0], parseInt(array3[1])]
        ]);

        // Optional; add a title and set the width and height of the chart
        var options = {'title':'Số lượng đơn hàng', 'width':1100, 'height':500};

        // Display the chart inside the <div> element with id="piechart"
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
</script>

</body>
</html>
