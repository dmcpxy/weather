<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="jacobooo">
    <title>Add Location</title>
    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="./css/dashboard.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Weather Data</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-1 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="locationList.jsp">Loc List</a></li>
                <li class="active"><a href="addLocation.jsp">Add Loc <span class="sr-only">(current)</span></a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-11 col-md-offset-1 main">
            <div id="loading"></div>
            <h1 class="page-header">Search</h1>
            <div class="row placeholders">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Location Name</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" placeholder="Please Input Location Name here">
                        </div>
                        <div class="col-sm-4">
                            <button type="button" id="searchLocationBtn" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
            <h2 class="sub-header">Result List</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Location</th>
                        <th>Country</th>
                        <th>Admin1</th>
                        <th>Lat(℃)</th>
                        <th>Lon(℃)</th>
                        <th>Url</th>
                        <th>FeatureCode</th>
                        <th>Timezone</th>
                        <th>asl(m)</th>
                        <th>Population</th>
                        <th>Distance(Km)</th>
                        <th>Operations</th>
                    </tr>
                    </thead>
                    <tbody id="resultLocationList">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.tmpl.min.js"></script>
<script type="text/x-jquery-tmpl" id="locationInfoTemplate">
   		<tr>
			<td name="name">{{= name}}</td>
			<td name="country">{{= country}}</td>
			<td name="admin1">{{= admin1}}</td>
			<td name="lat">{{= lat}}</td>
			<td name="lon">{{= lon}}</td>
			<td name="url">{{= url}}</td>
			<td name="featureCode">{{= featureCode}}</td>
			<td name="timezone">{{= timezone}}</td>
			<td name="asl">{{= asl}}</td>
			<td name="population">{{= population}}</td>
			<td name="distance">{{= distance}}</td>
			<td name="ops"><a href="#" >Add</a></td>
		</tr>
</script>
<script type="text/javascript">
    <%--onClick="saveLocation(this)"--%>
    $(function () {
        function saveLocation(elem) {
            var location = {};
            var siblings = $(elem).parent().siblings('td');
            siblings.each(function (index, td) {
                location[$(td).attr("name")] = $(td).text();
            });

            console.log(location);
        }

        $("#searchLocationBtn").bind('click', function () {
            var locationName = $("#name").val();
            if (locationName == '' || locationName.length == 0) {
                console.log("no input for location name");
                alert("no input for location name");
                return false;
            }
            $.getJSON("<%=request.getContextPath()%>/location/search", {name: locationName}, function (data) {
                if (!data || data.length == 0) {
                    alert("No Location Data Found...");
                }
                var $resultLocationList = $("#resultLocationList");
                $resultLocationList.empty();
                var trRows = $("#locationInfoTemplate").tmpl(data).appendTo($resultLocationList);
                $(trRows).each(function () {
                    $(this).find("a").bind('click', function () {
                        var location = {};
                        var siblings = $(this).parent().siblings('td');
                        siblings.each(function (index, td) {
                            location[$(td).attr("name")] = $(td).text();
                        });

                        $.post("<%=request.getContextPath()%>/location/add", location, function (response) {
                            console.log(response);
                            if (response['result'] == 0) {
                                alert("add successfully.");
                            }
                        });
                    });
                });
            });
        });

        $("#name").bind('keydown', function (event) {
            if (event.which == 13) {
                event.preventDefault();
                $("#searchLocationBtn").click();
            }
        });
    });
</script>
</body>
</html>
