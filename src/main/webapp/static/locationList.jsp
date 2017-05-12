<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="jacobooo">
    <title>Location List</title>
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
                <li class="active"><a href="locationList.jsp">Loc List <span class="sr-only">(current)</span></a></li>
                <li><a href="addLocation.jsp">Add Loc</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-11 col-md-offset-1 main">
            <h2 class="sub-header">Location List</h2>
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
                        <th>Distance</th>
                        <th>Operations</th>
                    </tr>
                    </thead>
                    <tbody id="locationDataList">
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
   		<tr id="location_{{:id}}">
			<td name="name">{{= name}}</td>
			<td name="country">{{= country}}</td>
			<td name="admin1">{{= admin1 }}</td>
			<td name="lat">{{= lat}}</td>
			<td name="lon">{{= lon}}</td>
			<td name="url">{{= url}}</td>
			<td name="featureCode">{{= featureCode}}</td>
			<td name="timezone">{{= timezone}}</td>
			<td name="asl">{{= asl}}</td>
			<td name="population">{{= population}}</td>
			<td name="distance">{{= distance}}</td>
			<td name="id" id="{{= id}}"><a href="#">Delete</a></td>
		</tr>
</script>
<script type="text/javascript">
    $(function () {
        $.getJSON("<%=request.getContextPath()%>/location/list", null, function (data) {
            var $locationDataList = $("#locationDataList");
            $locationDataList.empty();
            var trRows = $("#locationInfoTemplate").tmpl(data);
            trRows.appendTo($locationDataList);

            $(trRows).each(function () {
                $(this).find("a").bind('click', function () {
                    var locationId = $(this).parent().attr('id');
                    if (confirm("Are you sure to delete this location?")) {
                        $.post("<%=request.getContextPath()%>/location/delete", {"id": locationId}, function (response) {
                            if (response['result'] == 0) {
                                alert("delete successfully.");
                                window.location.reload();
                            }
                        });
                    }
                });
            });
        });
    });
</script>
</body>
</html>
