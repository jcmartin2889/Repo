
// **********************************************************************************
// function routeToLRPTask(taskId, taskType)
// Process a LRP task
//
// Parameters:
// taskId - the task id
// taskType - the task type
// optionId - the option id
//
// Return:
// True - if successful
// **********************************************************************************
function routeToLRPTask2(taskId, taskType, optionId)
{
	routeToLRPTask(taskId, taskType, optionId);
}

function routeToLRPTask(taskId, taskType, optionId)
{
	var currentFrame = getFrameCurrent();

	// Allow only if not currently in a transaction
	if(currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900018"));
		return false;
	}
	
	// option id must be specified
	if(optionId !='')
	{		
		currentFrame.location="function.jsp?name=&optionId=" + optionId + "&control=4" + "&taskId=" + taskId + "&taskType=" + taskType;
		return true;
	}
	else
	{
		errorAlert(20,getLanguageLabel("GBL900019"));
		return false;
	}
}


//**********************************************************************************
//function isLrpTask()
//Determine if the current task is an LRP task
//
//Parameters:
//None
//
//Return:
//True - if the current transaction is an LRP
//**********************************************************************************
function isLrpTask()
{
	var frame = getFrameCurrent();
	var obj = frame.document.getElementById(OBJ_TASKID);
	
	// no object exist
	if (obj == null)
	{
		return false;
	}
	
	// non-blank?
	if (obj.value.trim() != '')
	{
		return true;
	}
	
	// non-LRP task
	return false;
}


//**********************************************************************************
//function lrpInitialisation()
//Perform LRP initialisation
//
//Parameters:
//None
//
//Return:
//None
//**********************************************************************************
function lrpInitialisation()
{
	var gblCommentFrame = getCommentFrame();
	if (gblCommentFrame == null)
	{
		return;
	}
	var lrpTask = isLrpTask();
	
	// clear the comment
	var obj = gblCommentFrame.document.getElementById('commentDiv');
	if (obj != null && !lrpTask)
	{
		obj.innerHTML = '';
	}
	
	// setup the comment based on the html field
	var obj = gblCommentFrame.document.getElementById('commentField');
	if (obj != null)
	{
		obj.value = '';
		var frame = getFrameCurrent();
		var superMsgElement = frame.document.getElementById(OBJ_SUPMSG);
		if (superMsgElement != null)
		{
			obj.value = superMsgElement.value.trim();
		}
	}
	
	// enable disable comment field
	var obj = gblCommentFrame.document.getElementById('commentAddDiv');
	if (obj != null)
	{
		obj.style.visibility = lrpTask ? '' : 'hidden';
	}
}

//**********************************************************************************
// function lrpOnEnterSubmit()
// Perform LRP processing on submit
//
// Parameters:
// None
//
// Return:
// None
//**********************************************************************************
function lrpOnEnterSubmit()
{
	// non-lrp, ignore
	if (!isLrpTask())
	{
		return;
	}
	
	// non-Webfacing
	if (isWebFacing())
	{
		return;
	}

	// retrieve frame for main window
	var frame = getFrameCurrent();
	var superMsgElement = frame.document.getElementById(OBJ_SUPMSG);
	if (superMsgElement != null)
	{
		superMsgElement.value = rtvCommentField();
	}
}

//**********************************************************************************
// function rtvCommentField()
// Retrieve the comment
//
// Parameters:
// None
//
// Return:
// The comment in the comment field
//**********************************************************************************
function rtvCommentField()
{
	var commentFrame = getCommentFrame();
	if( commentFrame != null)
	{
		// retrieve the comment field element
		var obj = commentFrame.document.getElementById('commentField');
		if (obj != null)
		{
			return obj.value.trim();
		}
	}
	return '';
}


//**********************************************************************************
// function rtvTaskId()
// Retrieve the task id
//
// Parameters:
// None
//
// Return:
// The task id
//**********************************************************************************
function rtvTaskId()
{
	var frame = getFrameCurrent();
	var obj = frame.document.getElementById(OBJ_TASKID);
	return (obj == null) ? '' : obj.value;
}


//**********************************************************************************
// function removeCompletedTask(taskId)
// Remove the task from the workload list
//
// Parameters:
// Task id
//
// Return:
// True - if deleted
//**********************************************************************************
function removeCompletedTask(taskId)
{
	// no task id
	if (taskId.trim() == '')
	{
		return false;
	}
	
	// no frame?
	var gblNavigatorViewFrame = getFrameNavigatorView();
	if (gblNavigatorViewFrame == null)
	{
		return false;
	}
	
	// loop
	var mainCategoryList = gblNavigatorViewFrame.document.getElementById('eqWorkLoad');
	for (i=0; i<mainCategoryList.children.length; i++)
	{
		var dateList = mainCategoryList.children[i];
		
		// loop the content of the date
		for (j=1; j<dateList.children.length; j++)
		{
			var obj = dateList.children[j].children[0].children[1];
			
			var k=obj.children.length-1;
			while (k>=0)
			{
				var li = obj.children[k];
				if (li.id == taskId)
				{
					obj.removeChild(li);
					
					// exit once found
					return true;
				}
					
				// next child
				k--;
			}
		}
	}
}

//**********************************************************************************
// function getCommentFrame()
//
// Parameters:
// None
//
// Return:
// The Comment Frame 
//**********************************************************************************
function getCommentFrame()
{
	if (isUXP())
	{
		return getNamedFrame(getFrameTabBar().parent, 'uxpCommentsFrame');
	}
	else
	{
		return getFrameTab().frames['commentFrame'];
	}
}
