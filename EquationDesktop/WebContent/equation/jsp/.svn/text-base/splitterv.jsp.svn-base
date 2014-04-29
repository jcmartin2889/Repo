<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title>Splitter bar page</title>
	</head>
	<style>
	.splitterVBackground
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:0;
		background: url(../images/mback02.jpg) repeat;
		margin:0;
		border:0;
		padding:0;
		height:100%;
		width:5px;
	}
	.splitterHandleV
	{
		position:fixed;
		top:43%;
		left:2px;
		z-index:1;
		background: url(../images/grabHandleV.png) no-repeat center ;	
		height:19px;
		width:1px;
	}
	
	.splitterVGrabNormal
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterVerticalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:100%;
		width:5px;
		opacity:0.0;
		filter:alpha(opacity=0); /* For IE8 and earlier */
	}
	
	.splitterVGrabHover
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterVerticalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:100%;
		width:5px;
		opacity:0.9;
		filter:alpha(opacity=90); /* For IE8 and earlier */
		cursor: e-resize ; 
		background-size: cover; /*generic*/
	}

	.splitterVGrabFocus
	{
		position:fixed;
		top:0px;
		left:0px;
		z-index:1;
		background: url(../images/splitterVerticalHover.png) no-repeat center;
		margin:0;
		border:0;
		padding:0;
		height:100%;
		width:5px;
		opacity:0.9;
		filter:alpha(opacity=90); /* For IE8 and earlier */
		cursor: e-resize ;   
		/*
		filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='../images/splitterVerticalHover.png',sizingMethod='scale')";
		-ms-filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='../images/splitterVerticalHover.png',sizingMethod='scale')";
		*/
	}
	</style>

	<script>
		var dragit = {};
		
		function onload()
		{
			dragit.isOver = false;
			dragit.element = document.getElementById('splitterGrab');
			dragit.frameset = parent.document.getElementById('uxpTabFrameset');
		}
		
		function mouseover(e) 
		{
			dragit.isOver = true;
			if( !dragit.drag )
			{
				dragit.element.className = "splitterVGrabHover";	
			}
		}

		function mousedown(e)
		{
			dragit.element.setCapture();
			dragit.element.className = "splitterVGrabFocus";
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
			dragit.element.className = dragit.isOver ? "splitterVGrabHover": "splitterVGrabNormal";	
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
				dragit.element.className = "splitterVGrabNormal";	
			}
		}
		
		function resize(e)
		{
			// -2 to re-position the frame with the current mouse position in the middle 
			var newPos = self.frameElement.offsetLeft + e.clientX - 2;
			setTimeout(function ()
			{
				var newRows = newPos + ',5,*';
				if( newRows !== dragit.frameset.cols )
				{
					dragit.frameset.setAttribute('cols', newRows);
				}
   			},10);
		}
	</script>

	<body onload='onload();' onmouseover='mouseover(event);' onmousedown='mousedown(event);' onmousemove='mousemove(event);' onmouseup='mouseup(event);' onmouseout='mouseout(event);'  >
		<div id="hsplitterBackground"  class="splitterVBackground" />
		<div id="splitterHandle" class="splitterHandleV" />
		<div id="splitterGrab" class="splitterVGrabNormal" />
	</body>
</html>