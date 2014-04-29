<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title>Splitter bar page</title>
	</head>
	<style>
	.splitterBackground
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:0;
		background: url(../images/mback02.jpg) repeat;
		margin:0;
		border:0;
		padding:0;
		height:5px;
		width:100%;
	}
	.splitterHandleH
	{
		position:fixed;
		top:2px;
		left:49%;
		z-index:1;
		background: url(../images/grabHandle.png) no-repeat center ;	
		height:1px;
		width:19px;
	}
	
	.splitterGrabNormal
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterHorizontalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:5px;
		width:100%;
		opacity:0.0;
		filter:alpha(opacity=0); /* For IE8 and earlier */
	}
	
	.splitterGrabHover
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterHorizontalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:5px;
		width:100%;
		opacity:0.9;
		filter:alpha(opacity=90); /* For IE8 and earlier */
		cursor: s-resize ; 
	}

	.splitterGrabFocus
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterHorizontalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:5px;
		width:100%;
		opacity:0.9;
		filter:alpha(opacity=90); /* For IE8 and earlier */
		cursor: s-resize ;   
		background-size: cover; /*generic*/
		/*
		filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='../images/splitterHorizontalHover.png',sizingMethod='scale')";
		-ms-filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='../images/splitterHorizontalHover.png',sizingMethod='scale')";
		*/
	}
	</style>

	<script>
		var dragit = {};
		
		function onload()
		{
			dragit.isOver = false;
			dragit.element = document.getElementById('splitterGrab');
			dragit.frameset = parent.document.getElementById('txnFrameset');
		}
		
		function mouseover(e) 
		{
			dragit.isOver = true;
			if( !dragit.drag )
			{
				dragit.element.className = "splitterGrabHover";	
			}
		}

		function mousedown(e)
		{
			dragit.element.setCapture();
			dragit.element.className = "splitterGrabFocus";
			dragit.drag = true;
		}
		
		function mousemove(e)
		{
			if( dragit.drag)
			{
				resize(e);
			}
		}

		function mouseup(e)
		{
			dragit.element.releaseCapture();
			dragit.element.className = dragit.isOver ? "splitterGrabHover": "splitterGrabNormal";	
			if( dragit.drag )
			{
				dragit.drag = undefined;
				resize(e);
			}
		}

		function mouseout(e)
		{
			dragit.isOver = false;
			if( !dragit.drag )
			{
				dragit.element.className = "splitterGrabNormal";	
			}
		}
		
		function resize(e)
		{
			// -2 to re-position the frame with the current mouse position in the middle 
			var newPos = self.frameElement.offsetTop + e.clientY - 2;
			setTimeout(function ()
			{
				var newRows = newPos + ',5,*';
				if( newRows !== dragit.frameset.rows )
				{
					dragit.frameset.setAttribute('rows', newRows);
				}
   			},10);
		}
	</script>

	<body onload='onload();' onmouseover='mouseover(event);' onmousedown='mousedown(event);' onmousemove='mousemove(event);' onmouseup='mouseup(event);' onmouseout='mouseout(event);'  >
		<div id="hsplitterBackground"  class="splitterBackground" />
		<div id="splitterHandle" class="splitterHandleH" />
		<div id="splitterGrab" class="splitterGrabNormal" />
	</body>
</html>