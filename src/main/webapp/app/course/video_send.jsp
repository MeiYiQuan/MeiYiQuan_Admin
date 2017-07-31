<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div id="files">

</div>
</body>
<button onclick="shiyan()">文件上传</button>
<script type="text/javascript">
function shiyan(){
	div=document.getElementById("files");
	input=document.createElement("input");
	input.setAttribute("type", "file");
	div.appendChild(input);
}
</script>
</html>