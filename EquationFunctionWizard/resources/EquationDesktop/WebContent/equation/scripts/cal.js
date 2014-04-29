// *****************************************************************************
//  Javascript Calendar Widget
// -----------------------------
//  
//  Copyright and all other intellectual property rights in this software, 
//  in any form, is vested in Misys International Banking Systems Ltd ("Misys") 
//  or a related company.                                  
//                                                     
//  This software may not be copied, amended, compiled, translated, or developed
//  or sold, leased, hired, rented, or disclosed to any third party without the 
//  prior written consent of Misys.                     
//                                                     
//  Copyright © Misys International Banking Systems Ltd                           
// *****************************************************************************

// *****************************************************************************
//  DEFINITIONS
// *****************************************************************************
   
    // Always start the calArrWeekInits array with your string for
    // whatever calWeekStart (below) is set to.
    // calWeekStart determines the start of the week in the display
    // Set it to: 0 (Zero) for Sunday, 1 (One) for Monday etc..

    calWeekStart       =    0;

    // The week start day for the display is taken as the week start
    // for week numbering.  This ensures that only one week number
    // applies to one line of the calendar table.
    // [ISO 8601 begins the week with Day 1 = Monday.]

    // If you want to see week numbering on the calendar, set
    // this to true.  If not, false.

    calWeekNumberDisplay    = false;

    // Week numbering rules are generally based on a day in the week
    // that determines the first week of the year.  ISO 8601 uses
    // Thursday (day four when Sunday is day zero).  You can alter
    // the base day here.  

    calWeekNumberBaseDay    = 4;
                                       
   // How many years do want to be valid and to show in the drop-down list?

    var calDropDownYears   = 100;    
        
    // Each of the calendar's alert message types can be disabled
    // independently here.
    calShowInvalidDateMsg       = true;
    calShowOutOfRangeMsg        = true;
    calShowDoesNotExistMsg      = true;
    calShowInvalidAlert         = true;
    calShowDateDisablingError   = true;
    calShowRangeDisablingError  = true;
     
    // Set the allowed input date delimiters here...
    // E.g. To set the rising slash, hyphen, full-stop (aka stop or point),
    //      comma and space as delimiters use
    calArrDelimiters   = ['/','-','.',',',' '];

    // Set the format for the displayed 'Today' date and for the output
    // date here.
    //
    // The format is described using delimiters of your choice (as set
    // in calArrDelimiters above) and case insensitive letters D, M and Y.
    //
    // Definition               Returns
    // ----------               -------
    // D            date in the month without zero filling
    // DD           date in the month left zero filled
    // M            month number without zero filling
    // MM           month number left zero filled
    // MMM          month string from calArrMonthNames
    // YY           year number in two digits
    // YYYY         year number in four digits

      
    // The input date is fully parsed so a format is not required,
    // but there is no way to differentiate the sequence reliably.
    //
    // e.g. Is 05/08/03     5th August 2003,
    //                      8th May    2003 or even
    //                      3rd August 2005?
    //
    // So, you have to state how the code should interpret input dates.
    //
    // The sequence should always contain one D, one M and one Y only,
    // in any order.
	var eqDateSeq = getLanguageLabel('GBL700003');
	var dtFormat = "DMY";
	
	if(sessionType != SESSION_CLASSIC_DESKTOP)
	{
		var dtFormat = inpd;
		switch(dtFormat)
		{
			case "D"	: eqDateSeq = "DMY"; break;
			case "M"	: eqDateSeq = "MDY"; break;
			default		: eqDateSeq = "YMD"; break;
		}
	}

    calDateInputSequence = eqDateSeq;

	 // Displayed "Today" date format
	calDateDisplayFormat = eqDateSeq.substring(0,1).toLowerCase() + 
						   eqDateSeq.substring(0,1).toLowerCase() +
						   eqDateSeq.substring(1,2).toLowerCase() + 
						   eqDateSeq.substring(1,2).toLowerCase() +
						   eqDateSeq.substring(2,3).toLowerCase() + 
						   eqDateSeq.substring(2,3).toLowerCase();
    
    // Output date format
	// At this time ensure the input and output date formats are the same
	calDateOutputFormat = calDateDisplayFormat;


    // Note: Because the user may select a date then trigger the
    //       calendar again to select another, it is necessary to
    //       have the input date sequence in the same order as the
    //       output display format.  To allow the flexibility of having
    //       a full input date and a partial (e.g. only Month and Year)
    //       output, the input sequence is set separately.
    //
    //       The same reason determines that the delimiters used should
    //       be in calArrDelimiters.

    // calZindex controls how the pop-up calendar interacts with the rest
    // of the page.  It is usually adequate to leave it as 1 (One) but I
    // have made it available here to help anyone who needs to alter the
    // level in order to ensure that the calendar displays correctly in
    // relation to all other elements on the page.

    calZindex          = 5;

    // Personally I like the fact that entering 31-Sep-2005 displays
    // 1-Oct-2005, however you may want that to be an error.  If so,
    // set calBlnStrict = true.  That will cause an error message to
    // display and the selected month is displayed without a selected
    // day. Thanks to Brad Allan for his feedback prompting this feature.

    calBlnStrict       = false;

    // If you wish to disable any displayed day, e.g. Every Monday,
    // you can do it by setting the following array.  The array elements
    // match the displayed cells.
    //
    // You could put something like the following in your calling page
    // to disable all weekend days;
    //
    //  for (var i=0;i<calEnabledDay.length;i++)
    //      {if (i%7%6==0) calEnabledDay[i] = false;}
    //
    // The above approach will allow you to disable days of the week
    // for the whole of your page easily.  If you need to set different
    // disabled days for a number of date input fields on your page
    // there is an easier way: You can pass additional arguments to
    // calShow. The syntax is described at the top of this script in
    // the section:
    //    "How to use the Calendar once it is defined for your page:"
    //
    // It is possible to use these two approaches in combination.

    calEnabledDay      = [true, true, true, true, true, true, true,
                              true, true, true, true, true, true, true,
                              true, true, true, true, true, true, true,
                              true, true, true, true, true, true, true,
                              true, true, true, true, true, true, true,
                              true, true, true, true, true, true, true];

    // You can disable any specific date (e.g. 24-Jan-2006 or Today) by
    // creating an element of the array calDisabledDates as a date object
    // with the value you want to disable.  Date ranges can be disabled
    // by placing an array of two values (Start and End) into an element
    // of this array.

    calDisabledDates   = new Array();

    // e.g. To disable 10-Dec-2005:
    //          calDisabledDates[0] = new Date(2005,11,10);
    //
    //      or a range from 2004-Dec-25 to 2005-Jan-01:
    //          calDisabledDates[1] = [new Date(2004,11,25),new Date(2005,0,1)];
    //
    // Remember that Javascript months are Zero-based.

    // The disabling by date and date range does prevent the current day
    // from being selected.  Disabling days of the week does not so you can set
    // the calActiveToday value to false to prevent selection.     
    calActiveToday = "true";    
    
    // Dates that are out of the specified range can be displayed at the start
    // of the very first month and end of the very last.  Set
    // calOutOfRangeDisable to  true  to disable these dates (or  false  to
    // allow their selection).
    calOutOfRangeDisable = "true";
       
    // You can allow the calendar to be dragged around the screen by
    // using the setting calAllowDrag to true.
    // I can't say I recommend it because of the danger of the user
    // forgetting which date field the calendar will update when there
    // are multiple date fields on a page.    
    calAllowDrag = "false";
        
    // Closing the calendar by clicking on it (rather than elsewhere on the
    // main page) can be inconvenient.  The calClickToHide boolean value
    // controls this feature.
    calClickToHide = "false";
    
    // Anchor
    gblCalSourceEle = null;
    
    // Display the date widget in a new window?
    asPopupDateWindow = false;
    asPopupWindow = false;
    popupParam1 = null;
    popupParam2 = null;
    popupParam3 = null;
    
    // Determine the last calendar date click
    var lastCalendarDateClick = null;

// *****************************************************************************
// MAINLINE
// *****************************************************************************
	// Need to know if we need to write the html out when showing the calender
	var calStructureCreated = false;
	
	// Determine today's date    	
    var calTodaysDate;
    
    if(sessionType != SESSION_CLASSIC_DESKTOP)
    {
    	calTodaysDate = getUnitDate();
    }
    else
    {
    	calTodaysDate = new Date(Date.parse(new Date().toDateString()));   
    }
    
    var currentlySelectedDate = new Date();
    var pressedDate = new Date();   
    // Set the base year to be the current year minus half the number of dropdown options 
	var calBaseYear = calTodaysDate.getFullYear() - Math.abs(calDropDownYears/2);
	
	// Get list of months
	var months = getLanguageList("MONDSC");

    // Variables required by both calShow and calShowMonth

    var calTargetEle,
        calTriggerEle,
        calMonthSum            = 0,
        calBlnFullInputDate    = false,
        calPassEnabledDay      = new Array(),
        calSeedDate            = new Date(),
        calParmActiveToday     = true,
        calWeekStart           = calWeekStart%7,
        gblToday = getLanguageLabel('CALTOD').trim(),
        gblArrMonthNames = [months[0].trim(),
                            months[1].trim(),
                            months[2].trim(),
                            months[3].trim(),
                            months[4].trim(),
                            months[5].trim(),
                            months[6].trim(),
                            months[7].trim(),
                            months[8].trim(),
                            months[9].trim(),
                            months[10].trim(),
                            months[11].trim()],
        gblArrWeekInits = [getLanguageLabel('CALWK2').trim(),
        					getLanguageLabel('CALWK3').trim(),
      					    getLanguageLabel('CALWK4').trim(),
      					    getLanguageLabel('CALWK5').trim(),
     					    getLanguageLabel('CALWK6').trim(),
     					    getLanguageLabel('CALWK7').trim(),
        					getLanguageLabel('CALWK1').trim()],
        calInvalidDateMsg = getLanguageLabel('CALM01').trim(),
        calOutOfRangeMsg = getLanguageLabel('CALM02').trim(),
        calDoesNotExistMsg = getLanguageLabel('CALM03').trim(),
        calInvalidAlert = [getLanguageLabel('CALM04').trim(),
        						getLanguageLabel('CALM05').trim()],
        calDateDisablingError = [getLanguageLabel('CALERR').trim(),
        						getLanguageLabel('CALWK6').trim()],
        calRangeDisablingError = [getLanguageLabel('CALERR').trim(),
        						getLanguageLabel('CALWK7').trim()];

    
    //**********************************************************************************
	// Date.prototype.calFormat = function(calFormat)
	// Format the date correctly based on the value of the mask specified
	//
	// Parameters: 
	// calFormat: The calendar date mask/format required
	// **********************************************************************************     
    Date.prototype.calFormat = function(calFormat)
	{
	    var charCount = 0,
	         codeChar  = '',
	         result    = '';
				
		//Loop through the mask and format the output date depending on
		//the characters encountered.
		for (var i=0;i<=calFormat.length;i++)
		{
			if (i<calFormat.length && calFormat.charAt(i)==codeChar)
			{
				// If there are still characters left to process
				// and the current mask character is the same
				// as the previous one then add one to the length
				// of the current element definition                        
				charCount++;
			}
			else   
			{
				switch (codeChar)
				{
					case 'y': case 'Y':
						result += (this.getFullYear()%Math.pow(10,charCount)).toString().calPadLeft(charCount);
					break;
					
					case 'm': case 'M':
		            	// If we find an M, check the number of them to
						// determine whether to get the month number or
						// the month name.
						result += (charCount<3)
						?(this.getMonth()+1).
						toString().calPadLeft(charCount)
						:gblArrMonthNames[this.getMonth()];
					break;
						
					case 'd': case 'D':
						// If we find a D, get the date and format it
						result += this.getDate().toString().
						calPadLeft(charCount);
					break;
					
					default:
						// Copy any unrecognised characters across
						while (charCount-- > 0) 
						{
							result += codeChar;
						}
				}
				if (i<calFormat.length)
				{
					// Store the character we have just worked on
					codeChar  = calFormat.charAt(i);
					charCount = 1;
				}
			}
		}
		return result;
	};

    //**********************************************************************************
	// String.prototype.calPadLeft = function(padToLength)
	// Left pad a string
	//
	// Parameters:
	// padToLength: Length of padding required
	// ********************************************************************************** 
    String.prototype.calPadLeft = function(padToLength)
	{
		var result = '';
		for (var i=0;i<(padToLength - this.length);i++) {result += '0';}
		return (result + this);
	};


	// **********************************************************************************
	// function.prototype.runsAftercal  
	// Set up a closure so that any next function can be triggered
    // after the calendar has been closed AND that function can take
    // arguments.
	//
	// Parameters: none
	// **********************************************************************************    
    Function.prototype.runsAftercal = function()  
    {
    	var func = this, args = new Array(arguments.length);
    	
		for (var i=0;i<args.length;++i)
		{
			args[i] = arguments[i];
		}
		
		return function()
		{
			// concat/join the two argument arrays
        	for (var i=0;i<arguments.length;++i)
            {
            	args[args.length] = arguments[i];
			}
			
			return (args.shift()==calTriggerEle)?func.apply(this, args):null;
		};
	};

    // Use a global variable for the return value from the next action
    // IE fails to pass the function through if the target element is in
    // a form and calNextAction is not defined.

    var calNextActionReturn, calNextAction;

	// ****************************************************************************
	// Start of Function Library
	//
	//  Exposed functions:
	//
	//      calShow             Entry point for display of calendar,
	//                              called in main page.
	//      showCal             Legacy name of calShow:
	//                              Passes only legacy arguments,
	//                              not the optional day disabling arguments.
	//
	//      calShowMonth        Displays a month on the calendar,
	//                              Called when a month is set or changed	
	//
	//      calCancel           Called when the calendar background is clicked:
	//                              Calls calStopPropagation and may call calHide.
	//      calHide             Hides the calendar, called on various events.
	//      calStopPropagation  Stops the propagation of an event.
	//
	// ****************************************************************************


	// **********************************************************************************
	// function showCal(calEle,calSourceEle)  
	// Show the calendar
	//
	// Parameters:
	// calEle: The popup object 
	// calSourceEle: The input box
	// **********************************************************************************	
    function showCal(calEle,calSourceEle)    
    {
    	calShow(calEle,calSourceEle);
    }

    //**********************************************************************************
	// function calShowAsWindow(calEle,calSourceEle)  
	// Show the calendar in a new window
	//
	// Parameters:
	// calEle: The popup object 
	// calSourceEle: The input box
	// **********************************************************************************	
    function calShowAsWindow(calEle,calSourceEle)
	{
		// modal window open?
		if (isModalWindowOpenAlert(true))
		{
			return;
		}
		
		// setup the parameters
		popupParam1 = "CAL";
		popupParam2 = calEle;
		popupParam3 = calSourceEle;
		
		// open the new window
		openPopupAsWindow(0,0, calSourceEle.id, 'popup.jsp');
	}
				
    
    //**********************************************************************************
	// function calShow(calEle,calSourceEle)
	// Show the calendar
	//
	// Parameters:
	// calEle: The popup object 
	// calSourceEle: The input box
	// **********************************************************************************	
    function calShow(calEle,calSourceEle)
	{
		// modal window open?
		if (isModalWindowOpenAlert(false))
		{
			return;
		}
		
		// determine if same button has been clicked again
		if (isClickSameField(calEle, lastCalendarDateClick))
		{
			return;
		}
		
		// store this
		lastCalendarDateClick = calEle;
		
		// Displayed "Today" date format
		calDateDisplayFormat = eqDateSeq.substring(0,1).toLowerCase() + 
							   eqDateSeq.substring(0,1).toLowerCase() +
							   eqDateSeq.substring(1,2).toLowerCase() + 
							   eqDateSeq.substring(1,2).toLowerCase() +
							   eqDateSeq.substring(2,3).toLowerCase() + 
							   eqDateSeq.substring(2,3).toLowerCase();
	    
		// Date format.  If this is an 8-digit format date field, then make sure to change the YY into YYYY
		if ((calEle.fieldtype == null || calEle.fieldtype=="D") && calEle.maxLength==8 && calEle.value.trim().length!=6)
		{
			calDateDisplayFormat = calDateDisplayFormat.replace("yy", "yyyy");
			
			// Set Year drop down list starting point and overall range for 8 digit dates
			// as per Equation input range of 1901-2173 (corresponds to Day of Century range 1-99999)
			calBaseYear = 1901;
			calDropDownYears = 273;	
		}
		else
		{
			// Set Year drop down list starting point and overall range for 6 digit dates
			// as per Equation input range of 1950-2049 
			calBaseYear = 1950;
			calDropDownYears = 100; 
		}
		
	    // Output date format
		// At this time ensure the input and output date formats are the same
		calDateOutputFormat = calDateDisplayFormat;
				
		// anchor tag
		gblCalSourceEle = calSourceEle;
	
		// Create the structure if we haven't already done so.
		if (!calStructureCreated)
		{
			createStructure();
			structureCreated = true;
		}
		pressedDate = calEle.value;	
		calTriggerEle = calSourceEle;

		// Take any parameters that there might be from the third onwards as
		// day numbers to be disabled 0 = Sunday through to 6 = Saturday.
         
		calParmActiveToday = true;

		for (var i=0;i<7;i++)
		{
         	calPassEnabledDay[(i+7-calWeekStart)%7] = true;
            for (var j=2;j<arguments.length;j++)
			{
				if (arguments[j]==i)
            	{
            		calPassEnabledDay[(i+7-calWeekStart)%7] = false;
					if (calTodaysDate.getDay()==i)
					{
						calParmActiveToday = false;
					}
				}
            }
		}

         // If no value is preset then the seed date is
         // Today (when today is in range) OR
         // The middle of the date range.

		calSeedDate = calTodaysDate;

		// Strip space characters from start and end of date input
		calEle.value = calEle.value.replace(/^\s+/,'').replace(/\s+$/,'');

		document.getElementById('calMonths').options.length = 0;
		
		for (i=0;i<gblArrMonthNames.length;i++)
		{
			document.getElementById('calMonths').options[i] =
			new Option(gblArrMonthNames[i],gblArrMonthNames[i]);
		}

		document.getElementById('calYears').options.length = 0;
         
		for (i=0;i<calDropDownYears;i++){
			if(calBaseYear+i <(calBaseYear + calDropDownYears)){ //set maximum year, as per EQUATION
				document.getElementById('calYears').options[i] = new Option((calBaseYear+i),(calBaseYear+i));
			}
		}

		for (i=0;i<gblArrWeekInits.length;i++)
		{
			document.getElementById('calWeekInit' + i).innerHTML =
			gblArrWeekInits[(i+calWeekStart)%
			gblArrWeekInits.length];
		}
		if (document.getElementById('calFoot'))
		{
			var outToday = calTodaysDate.calFormat(calDateDisplayFormat);
			
			if(sessionType != SESSION_CLASSIC_DESKTOP)
			{
				if(outToday.length == 8)
				{
					if(dtFormat == "Y")
					{
						outToday = outToday.substring(2,4) + '/' + outToday.substring(4,6) + '/' + outToday.substring(6,8);
					}
					else
					{
						outToday = outToday.substring(0,2) + '/' + outToday.substring(2,4) + '/' + outToday.substring(6,8);
					}			
				}
				else
				{
					outToday = outToday.substring(0,2) + '/' + outToday.substring(2,4) + '/' + outToday.substring(4,6);
				}
			}
			else
			{
				outToday = outToday.substring(0,2) + '/' + outToday.substring(2,4) + '/' + outToday.substring(4,6);
			}
		}
		document.getElementById('calFoot').innerHTML = gblToday + ' ' + outToday;

		if (calEle.value.length==0 || calEle.value != parseInt(calEle.value,10)
			&& calEle.value != ('0' + parseFloat(calEle.value,10).toString()))		
		{
			// If no value is entered and today is within the range,
			// use today's date, otherwise use the middle of the valid range.
			calBlnFullInputDate=false;
			if ((new Date(calBaseYear+calDropDownYears-1,11,31))<calSeedDate ||
                (new Date(calBaseYear,0,1))>calSeedDate)
            {
            	calSeedDate = new Date(calBaseYear +
				Math.floor(calDropDownYears / 2), 5, 1);
			}
		}
		else
		{
			function calInputFormat(calEleValue)
			{
				var calArrSeed = new Array();
                var calArrInput = new Array();     
				if(calDateDisplayFormat == "ddmmyy" )
				{				
					calArrInput[0] = calEle.value.substring(0, 2);
					calArrInput[1] = calEle.value.substring(2, 4);
					calArrInput[2] = calEle.value.substring(4, 6);
				}
				else if(calDateDisplayFormat == "ddmmyyyy")
				{				
					calArrInput[0] = calEle.value.substring(0, 2);
					calArrInput[1] = calEle.value.substring(2, 4);
					calArrInput[2] = calEle.value.substring(4);
				}
				else if(calDateDisplayFormat == "mmddyy" )
				{				
					calArrInput[0] = calEle.value.substring(0, 2);
					calArrInput[1] = calEle.value.substring(2, 4);
					calArrInput[2] = calEle.value.substring(4, 6);
				}
				else if(calDateDisplayFormat == "mmddyyyy")
				{				
					calArrInput[0] = calEle.value.substring(0, 2);
					calArrInput[1] = calEle.value.substring(2, 4);
					calArrInput[2] = calEle.value.substring(4);
				}
				else if(calDateDisplayFormat == "yymmdd" )
				{				
					calArrInput[0] = calEle.value.substring(0, 2);
					calArrInput[1] = calEle.value.substring(2, 4);
					calArrInput[2] = calEle.value.substring(4, 6);
				}
				else if(calDateDisplayFormat == "yyyymmdd")
				{				
					calArrInput[0] = calEle.value.substring(0, 4);
					calArrInput[1] = calEle.value.substring(4, 6);
					calArrInput[2] = calEle.value.substring(6);
				}
				else 
				{
					// let it check for delimeter
					calArrInput = calEle.value.
                                    split(new RegExp('[\\'+calArrDelimiters.
                                                        join('\\')+']+','g'));
				}

				// "Escape" all the user defined date delimiters above -
				// several delimiters will need it and it does no harm for
                // the others.

                // Strip any empty array elements (caused by delimiters)
                // from the beginning or end of the array. They will
                // still appear in the output string if in the output
                // format.

				if (calArrInput[0].length==0) calArrInput.splice(0,1);

				if (calArrInput[calArrInput.length-1].length==0)
					calArrInput.splice(calArrInput.length-1,1);

 				calBlnFullInputDate = false;

				switch (calArrInput.length)
				{
 					case 1:
					{
						// Year only entry
                        calArrSeed[0] = parseInt(calArrInput[0],10);   // Year
                        calArrSeed[1] = '6';                           // Month
                        calArrSeed[2] = 1;                             // Day
                        break;
					}
					case 2:
					{
						// Year and Month entry
                        calArrSeed[0] =
                        parseInt(calArrInput[calDateInputSequence.
                                                   replace(/D/i,'').
                                                   search(/Y/i)],10);  // Year
                        calArrSeed[1] = calArrInput[calDateInputSequence.
                                                   replace(/D/i,'').
                                                   search(/M/i)];      // Month
                        calArrSeed[2] = 1;                             // Day
                        break;
                    }
					case 3:
					{
                        // Day Month and Year entry
						calArrSeed[0] =
                        parseInt(calArrInput[calDateInputSequence.
                                                   search(/Y/i)],10);  // Year
                        calArrSeed[1] = calArrInput[calDateInputSequence.
                                                   search(/M/i)];      // Month
                        calArrSeed[2] =
                            parseInt(calArrInput[calDateInputSequence.
                                                   search(/D/i)],10);  // Day

                        calBlnFullInputDate = true;
                        break;
                     }
					default:
					{
						// A stuff-up has led to more than three elements in
                        // the date.
                        calArrSeed[0] = 0;     // Year
                        calArrSeed[1] = 0;     // Month
                        calArrSeed[2] = 0;     // Day
                    }
				}

				// These regular expressions validate the input date format
				// to the following rules;
				//         Day   1-31 (optional zero on single digits)
				//         Month 1-12 (optional zero on single digits)
				//                     or case insensitive name
				//         Year  One, Two or four digits
				
				// Months names are as set in the language-dependent
				// definitions and delimiters are set just below there

                var calExpValDay    = /^(0?[1-9]|[1-2]\d|3[0-1])$/,
                    calExpValMonth  = new RegExp('^(0?[1-9]|1[0-2]|'        +
                                                 gblArrMonthNames.join('|') +
                                                 ')$','i'),
                    calExpValYear   = /^(\d{1,2}|\d{4})$/;

                // Apply validation and report failures

                if (calExpValYear.exec(calArrSeed[0])  == null ||
                    calExpValMonth.exec(calArrSeed[1]) == null ||
                    calExpValDay.exec(calArrSeed[2])   == null)
                {
					if (calShowInvalidDateMsg) //no warning for now
					{
					 //errorAlert(20,calInvalidDateMsg + calInvalidAlert[0] + calEleValue + calInvalidAlert[1]);
					 //calEleValue = '';
					 //pressedDate = '';
					 try
					 {
					 	//calTargetEle.value = '';					 
					 	//calInputEle.value = '';
					 }
					 	catch(e){}
					}
                    calBlnFullInputDate = false;
                    calArrSeed[0] = calBaseYear +
                                    Math.floor(calDropDownYears/2); // Year
                    calArrSeed[1] = '6';                            // Month
					calArrSeed[2] = 1;                              // Day
				}

                 // Return the  Year    in calArrSeed[0]
                 //             Month   in calArrSeed[1]
                 //             Day     in calArrSeed[2]

                 return calArrSeed;
			}
			
			// Parse the string into an array using the allowed delimiters
			calArrSeedDate = calInputFormat(calEle.value);

            // So now we have the Year, Month and Day in an array.
            //   If the year is one or two digits then the routine assumes a
            //   year belongs in the 21st Century unless it is less than 50
            //   in which case it assumes the 20th Century is intended.
            if (calArrSeedDate[0]<100) calArrSeedDate[0] += (calArrSeedDate[0]>50)?1900:2000;

            // Check whether the month is in digits or an abbreviation
            if (calArrSeedDate[1].search(/\d+/)!=0)
            {
            	month = gblArrMonthNames.join('|').toUpperCase().search(calArrSeedDate[1].substr(0,3).toUpperCase());
                calArrSeedDate[1] = Math.floor(month/4)+1;
            }
			calSeedDate = new Date(calArrSeedDate[0], calArrSeedDate[1]-1, calArrSeedDate[2]);
		}

		// Test that we have arrived at a valid date

		if (isNaN(calSeedDate))
		{
			if (calShowInvalidDateMsg) errorAlert(20,  calInvalidDateMsg + calInvalidAlert[0] + calEle.value + calInvalidAlert[1]);
             calSeedDate = new Date(calBaseYear + Math.floor(calDropDownYears/2),5,1);
             calBlnFullInputDate=false;
		}
		else
		{
			// Test that the date is within range,
			// if not then set date to a sensible date in range.

			if ((new Date(calBaseYear,0,1)) > calSeedDate)
			{
				if (calBlnStrict && calShowOutOfRangeMsg) errorAlert(20,calOutOfRangeMsg);
                calSeedDate = new Date(calBaseYear,0,1);
                calBlnFullInputDate=false;
			}
			else
			{
				if ((new Date(calBaseYear+calDropDownYears-1,11,31))< calSeedDate)
            	{
            		if (calBlnStrict && calShowOutOfRangeMsg) errorAlert(20,calOutOfRangeMsg);
					calSeedDate = new Date(calBaseYear +
					Math.floor(calDropDownYears)-1,11,1);
					calBlnFullInputDate=false;
				}
				else
				{
					if (calBlnStrict && calBlnFullInputDate && (calSeedDate.getDate() != calArrSeedDate[2] ||
                    (calSeedDate.getMonth()+1) != calArrSeedDate[1] || calSeedDate.getFullYear()  != calArrSeedDate[0]))
					{
						if (calShowDoesNotExistMsg) errorAlert(20,calDoesNotExistMsg);
						calSeedDate = new Date(calSeedDate.getFullYear(),calSeedDate.getMonth()-1,1);
						calBlnFullInputDate=false;
					}
				}
			}
		}

		// Test the disabled dates for validity
		// Give error message if not valid.

		for (var i=0;i<calDisabledDates.length;i++)
		{
			if (!((typeof calDisabledDates[i] == 'object') && (calDisabledDates[i].constructor == Date)))
            {
            	if ((typeof calDisabledDates[i] == 'object') && (calDisabledDates[i].constructor == Array))
                {
					var calPass = true;

					if (calDisabledDates[i].length !=2)
					{
						if (calShowRangeDisablingError) errorAlert(20, calRangeDisablingError[0] +  calDisabledDates[i] +  calRangeDisablingError[1]);
                         calPass = false;
					}
					else
                    {
                    	for (var j=0;j<calDisabledDates[i].length;j++)
                        {
                        	if (!((typeof calDisabledDates[i][j] == 'object') &&
                                   (calDisabledDates[i][j].constructor == Date)))
     						{
     							if (calShowRangeDisablingError) errorAlert(20, calDateDisablingError[0] + calDisabledDates[i][j] + calDateDisablingError[1]);
                                 calPass = false;
                            }
						}
					}

					if (calPass && (calDisabledDates[i][0] > calDisabledDates[i][1]))
                    {
                    	calDisabledDates[i].reverse();
                    }
				}
				else
				{
					if (calShowRangeDisablingError)
                    errorAlert(20, calDateDisablingError[0] +
								calDisabledDates[i] +
								calDateDisablingError[1]);
				}
			}
		}

       	// Calculate the number of months that the entered (or
     	// defaulted) month is after the start of the allowed
       	// date range.

		calMonthSum =  12*(calSeedDate.getFullYear()-calBaseYear) + calSeedDate.getMonth();

		// Set the drop down boxes.

		document.getElementById('calYears').options.selectedIndex = Math.floor(calMonthSum/12);
		document.getElementById('calMonths').options.selectedIndex = (calMonthSum%12);
	
		// Display the month
		calShowMonth(0);
	
		// Position the calendar box (relative to the button, not the input)
		var offsetTop =parseInt(calSourceEle.offsetTop ,10);		
		offsetLeft=parseInt(calSourceEle.offsetLeft,10) + parseInt(calSourceEle.offsetWidth,10) - 15;
		
		calTargetEle=calEle;

		do 
		{
         	calEle=calEle.offsetParent;
			offsetTop +=parseInt(calEle.offsetTop,10);
			offsetLeft+=parseInt(calEle.offsetLeft,10);
		}
		while (calEle.tagName!='BODY' && calEle.tagName!='HTML');
		
		// get all the element
		var calWidgetContainerDIV = document.getElementById('calWidgetContainerDIV');
		var calIframe = document.getElementById('calIframe');
		var calWidgetContainer = document.getElementById('calWidgetContainer');
		var calWidgetHeader = document.getElementById('calWidgetHeader');
		var calWidgetBody = document.getElementById('calWidgetBody');
		
		// move the divvie around	
		calWidgetContainerDIV.style.top =offsetTop +'px';
		calWidgetContainerDIV.style.left=offsetLeft+'px';		
         	             	
        if (calIframe)
		{
            calIframe.style.top=offsetTop +'px';
            calIframe.style.left=offsetLeft+'px';
            calIframe.style.width=(calWidgetContainer.offsetWidth)+'px';
			calIframe.style.height=(calWidgetContainer.offsetHeight)+'px';
			calIframe.style.visibility='visible';
        }

	    //Get size of the internal widget
		var testwidth = document.getElementById('cal').clientWidth;

		// Resize header and container to fit
		calWidgetHeader.style.width = testwidth +'px';	  
		calWidgetBody.style.width = testwidth +'px';	  
		calWidgetContainer.style.width = testwidth +'px';
		calWidgetContainerDIV.style.width = testwidth +'px';

		var testheight = document.getElementById('cal').clientHeight;
		calWidgetBody.style.height = testheight +'px';	

		// RTL
		if (RTL)
		{
			var l = offsetLeft - testwidth - 25;
			calWidgetContainerDIV.style.left=l+'px';
		}

       	// Show it on the page
       	//document.getElementById('calWidgetContainer').style.visibility='visible';
       	//document.getElementById('cal').style.visibility='visible';
       	
       	// Adjust the size of the window
       	if (asPopupWindow)
       	{
			width = calWidgetBody.offsetWidth;
			height = calWidgetContainerDIV.offsetHeight;
			adjustWidthHeight(width,height);
			adjustLeftTop(width,height);
			
			// retrieve the windows width
			clientWidthHeight = getClientWidthHeight();
			if (clientWidthHeight[0] > width)
			{
				calWidgetHeader.style.width = clientWidthHeight[0] +'px';	  
				calWidgetBody.style.width = clientWidthHeight[0] +'px';	  
				calWidgetContainer.style.width = clientWidthHeight[0] +'px';
				calWidgetContainerDIV.style.width = clientWidthHeight[0] +'px';
			}
       		CALPopup.showPopup(calSourceEle.id);
		}
		else if (isUXP())
		{
			gblCalSourceEle = calTargetEle;
			CALPopup.showPopupBelow(calTargetEle.id);
		}
		else
		{
			CALPopup.showPopup(calSourceEle.id);
		}
		
        if (typeof event=='undefined')
		{				
			calSourceEle.parentNode.
			addEventListener('click',calStopPropagation,false);
		}
		else
		{
			if(event!=null)
			{
				event.cancelBubble = true;
			}
		}
        
        // set focus
        document.getElementById('calYears').focus();
	}
		
	// **********************************************************************************
	// function calHide()  
	// Hide the calendar
	//
	// Parameters: None
	// **********************************************************************************
	function calHide()
    {
	    CALPopup.hidePopup();
	    isClickSameField(1,2);
	    //CALPopup2.hidePopup();
  
    	try
    	{
			if(calTargetEle.value != pressedDate)
			{
				if(calTargetEle.value != '')
				{
					calTargetEle.value = pressedDate;
				}
        	}	
        }
        catch(e)
        {
        }
      
         
        // document.getElementById('calWidgetContainerDIV').style.display = 'none';
        // document.getElementById('calWidgetContainer').style.display='none';
        // document.getElementById('cal').style.display='none';
        
        
		if (document.getElementById('calIframe'))
        {
    	   	document.getElementById('calIframe').style.display='none';
        }

		if (typeof calNextAction!='undefined' && calNextAction!=null)
        {
           	calNextActionReturn = calNextAction();
			// Explicit null set to prevent closure causing memory leak
			calNextAction = null;
		}
		
		// close the window		
		if (asPopupWindow)
		{
			window.close();
		}			
		
	}
	
	// **********************************************************************************
	// function calCancel(calEvt)  
	// Cancel the calendar event
	//
	// Parameters: 
	// calEvt: The event to be cancelled
	// **********************************************************************************
	function calCancel(calEvt)
	{	    
	  //  if (calClickToHide) calHide();
	 //   calStopPropagation(calEvt);
	}
		
	// **********************************************************************************
	// function calStopPropagation(calEvt)  
	// Cancel the propagation event
	//
	// Parameters: 
	// calEvt: The event to be cancelled
	// **********************************************************************************
	function calStopPropagation(calEvt)
	{   
		if (calEvt.stopPropagation) calEvt.stopPropagation();     // Capture phase
		else calEvt.cancelBubble = true;   // Bubbling phase
	}
	
	
	// **********************************************************************************
	// function calShowMonth(calBias) 
	// Show the Month
	//
	// Parameters:
	// calBias: Months to shift from current
	// **********************************************************************************
	function calShowMonth(calBias)
	{
		// Set the selectable Month and Year
        // May be called: from the left and right arrows
        //                  (shift month -1 and +1 respectively)
        //                from the month selection list
        //                from the year selection list
        //                from the showCal routine
        //                  (which initiates the display).

        var calShowDate  = new Date(Date.parse(new Date().toDateString())), calStartDate = new Date();

        calSelYears  = document.getElementById('calYears');
        calSelMonths = document.getElementById('calMonths');

        if (calSelYears.options.selectedIndex>-1)
		{
			calMonthSum=12*(calSelYears.options.selectedIndex)+calBias;
            if (calSelMonths.options.selectedIndex>-1)
			{
				calMonthSum+=calSelMonths.options.selectedIndex;
			}
		}
        else
		{
			if (calSelMonths.options.selectedIndex>-1)
			{
				calMonthSum+=calSelMonths.options.selectedIndex;
			}
		}

        calShowDate.setFullYear(calBaseYear + Math.floor(calMonthSum/12), (calMonthSum%12), 1);

        // If the Week numbers are displayed, shift the week day names
        // to the right.
        document.getElementById('calWeek_').style.display=
            (calWeekNumberDisplay)
               ?((document.getElementById('calIFrame')||
                  document.getElementById('cal'))?'block':'table-cell')
               :'none';

        if ((12*parseInt((calShowDate.getFullYear()-calBaseYear),10)) +
            parseInt(calShowDate.getMonth(),10) < (12*calDropDownYears)  &&
            (12*parseInt((calShowDate.getFullYear()-calBaseYear),10)) +
            parseInt(calShowDate.getMonth(),10) > -1)
           {calSelYears.options.selectedIndex=Math.floor(calMonthSum/12);
            calSelMonths.options.selectedIndex=(calMonthSum%12);

            calCurMonth = calShowDate.getMonth();

            calShowDate.setDate((((calShowDate.getDay()-calWeekStart)<0)?-6:1)+calWeekStart-calShowDate.getDay());

            calStartDate = new Date(calShowDate);

            var calFoot = document.getElementById('calFoot');

			//**********************************************************************************
			// function calFootOutput() 
			// Set the Footer output
			//
			// Parameters: none		
			// **********************************************************************************

            function calFootOutput() 
            {             
           		calSetOutput(calTodaysDate);
           		currentlySelectedDate = calTodaysDate;           		
				var temppb = pressedDate;
				lastCalendarDateClick = null;
				calShow(calTargetEle,calTriggerEle);
				pressedDate = temppb;					
            }
	
			
			if (calDisabledDates.length==0)
			{
				if (calActiveToday && calParmActiveToday)
                {
					calFoot.onclick     = calFootOutput;
					calFoot.className   = 'calFoot';

					if (document.getElementById('calIFrame'))
					{
						calFoot.onmouseover  = calChangeClass;
						calFoot.onmouseout   = calChangeClass;
					}
				}
                else
                {
                	calFoot.onclick     = null;
                    calFoot.className   = 'calFootDisabled';

                    if (document.getElementById('calIFrame'))
                    {
                    	calFoot.onmouseover  = null;
                        calFoot.onmouseout   = null;
                    }

                    if (document.addEventListener)
                    {
                    	calFoot.addEventListener('click', calStopPropagation, false);
					}
					else   
					{
						calFoot.attachEvent('onclick', calStopPropagation);
					}
				}
			}
			else
			{
				for (var k=0;k<calDisabledDates.length;k++)
				{
					if (!calActiveToday || !calParmActiveToday ||
						((typeof calDisabledDates[k] == 'object') &&
						(((calDisabledDates[k].constructor == Date) &&
						calTodaysDate.valueOf() == calDisabledDates[k].valueOf()) ||
   						((calDisabledDates[k].constructor == Array)     &&
						calTodaysDate.valueOf() >= calDisabledDates[k][0].valueOf() &&
						calTodaysDate.valueOf() <= calDisabledDates[k][1].valueOf()))))
					{
						calFoot.onclick     = null;
                        calFoot.className   = 'calFootDisabled';

                        if (document.getElementById('calIFrame'))
						{
							calFoot.onmouseover  = null;
                            calFoot.onmouseout   = null;
						}

						if (document.addEventListener)
 						{
 							calFoot.addEventListener('click',calStopPropagation,false);
						}
						else   
						{
							calFoot.attachEvent('onclick',calStopPropagation);
						}
                        break;
					}
                    else
                    {
                    	calFoot.onclick=calFootOutput;
                        calFoot.className='calFoot';

						if (document.getElementById('calIFrame'))
						{
							calFoot.onmouseover = calChangeClass;
							calFoot.onmouseout  = calChangeClass;
						}
					}
				}
			}
				
			//**********************************************************************************
			// function calSetOutput(calOutputDate) 
			// Set the output Date
			//
			// Parameters:
			// calOutputDate: The output date to set
			// **********************************************************************************
            function calSetOutput(calOutputDate)
			{
				calTargetEle.value = calOutputDate.calFormat(calDateOutputFormat);
			}

			//**********************************************************************************
			// function calCellOutput(calEvt)
			// Set the output Date
			//
			// Parameters:
			// calEvt: The calendar event
			// **********************************************************************************
			function calCellOutput(calEvt)
			{	
				var calEle = calEventTrigger(calEvt), calOutputDate = new Date(calStartDate);				
				if (calEle.nodeType==3) calEle=calEle.parentNode;
				calOutputDate.setDate(calStartDate.getDate() +  parseInt(calEle.id.substr(8),10));
				currentlySelectedDate = calOutputDate;				
				calSetOutput(currentlySelectedDate);
				var temppb = pressedDate;	
				
				//firefox fix for ok button	
				//Only do in firefox so we don't waste resources. 
				if(!document.all )
				{
					CALPopup.hidePopup();
					isClickSameField(1,2);
					//CALPopup2.hidePopup();
	      		    //document.getElementById('calWidgetContainerDIV').style.display = 'none';
	      		    //document.getElementById('calWidgetContainer').style.display='none';
	     		    //document.getElementById('cal').style.display='none';
	     		    if(document.getElementById('calIframe')){
	     		    	document.getElementById('calIframe').style.display='none';	
	     		    }
     		    }
				lastCalendarDateClick = null;
				calShow(calTargetEle,calTriggerEle);
				pressedDate = temppb;	
				
				calOkButtonClick();				
			}

			//**********************************************************************************
			// function calChangeClass(calEvt)
			// Change the class 
			//
			// Parameters:
			// calEvt: The event
			// **********************************************************************************
			function calChangeClass(calEvt)
			{				
				var calEle = calEventTrigger(calEvt);

				if (calEle.nodeType==3) calEle=calEle.parentNode;

	            switch (calEle.className)
				{
					case 'calCells':
	                   calEle.className = 'calCellsHover';
	                   break;
	                case 'calCellsHover':
	                   calEle.className = 'calCells';
	                   break;
	                case 'calCellsExMonth':
	                   calEle.className = 'calCellsExMonthHover';
	                   break;
	                case 'calCellsExMonthHover':
	                   calEle.className = 'calCellsExMonth';
	                   break;
	                case 'calCellsWeekend':
	                   calEle.className = 'calCellsWeekendHover';
	                   break;
	                case 'calCellsWeekendHover':
	                   calEle.className = 'calCellsWeekend';
	                   break;
	                case 'calFoot':
	                   calEle.className = 'calFootHover';
	                   break;
	                case 'calFootHover':
	                   calEle.className = 'calFoot';
	                   break;
	                case 'calInputDate':
	                   calEle.className = 'calInputDateHover';
	                   break;
	                case 'calInputDateHover':
	                   calEle.className = 'calInputDate';
	                case 'calCellsExMonthWeekend':
		                   calEle.className = 'calCellsExMonthWeekendHover';
		                   break;
		                case 'calCellsExMonthWeekendHover':
		                   calEle.className = 'calCellsExMonthWeekend';
		                   break;
				}
				return true;
			}

			//**********************************************************************************
			// function calEventTrigger(calEvt)
			// Trigger an event
			//
			// Parameters:
			// calEvt: The event
			// **********************************************************************************
			function calEventTrigger(calEvt)
 			{ 				
 				if (!calEvt) calEvt = event;
				return calEvt.target||calEvt.srcElement;
			}
			
			//**********************************************************************************
			// function calWeekNumber(calInDate)
			// Set the week number
			//
			// Parameters:
			// calInDate: input date
			// **********************************************************************************
			function calWeekNumber(calInDate)
			{
				// The base day in the week of the input date
                var calInDateWeekBase = new Date(calInDate);

                calInDateWeekBase.setDate(calInDateWeekBase.getDate()
                                           - calInDateWeekBase.getDay()
                                           + calWeekNumberBaseDay
                                           + ((calInDate.getDay()>
                                               calWeekNumberBaseDay)?7:0));

                // The first Base Day in the year
                var calFirstBaseDay = new Date(calInDateWeekBase.getFullYear(),0,1);

                calFirstBaseDay.setDate(calFirstBaseDay.getDate() - calFirstBaseDay.getDay() + calWeekNumberBaseDay);

				if (calFirstBaseDay < new Date(calInDateWeekBase.getFullYear(),0,1))
                {
                	calFirstBaseDay.setDate(calFirstBaseDay.getDate()+7);
                }

                // Start of Week 01
                var calStartWeekOne = new Date(calFirstBaseDay
                                                - calWeekNumberBaseDay
                                                + calInDate.getDay());

				if (calStartWeekOne > calFirstBaseDay)
				{
					calStartWeekOne.setDate(calStartWeekOne.getDate()-7);
                }

                // Subtract the date of the current week from the date of the
                // first week of the year to get the number of weeks in
                // milliseconds.  Divide by the number of milliseconds
                // in a week then round to no decimals in order to remove
                // the effect of daylight saving.  Add one to make the first
                // week, week 1.  Place a string zero on the front so that
                // week numbers are zero filled.

                var calWeekNo =
                    '0' + (Math.round((calInDateWeekBase -
                                       calFirstBaseDay)/604800000,0) + 1);

                // Return the last two characters in the week number string

                return calWeekNo.substring(calWeekNo.length-2,
                                           calWeekNo.length);
			}

            // Treewalk to display the dates.
            // I tried to use getElementsByName but IE refused to cooperate
            // so I resorted to this method which works for all tested
            // browsers.

            var calCells = document.getElementById('calCells');

            for (i=0;i<calCells.childNodes.length;i++)
            {
				var calRows = calCells.childNodes[i];
                if (calRows.nodeType==1 && calRows.tagName=='TR')
                {
					if (calWeekNumberDisplay)
 					{
 						//Calculate the week number using calShowDate
                        calRows.childNodes[0].innerHTML = calWeekNumber(calShowDate);
                        calRows.childNodes[0].style.display= (document.getElementById('calIFrame')||
                            document.getElementById('cal'))
                               ?'block'
                               :'table-cell';
  					}
                    else
                    {
                    	calRows.childNodes[0].style.display='none';
                    }

                    for (j=1;j<calRows.childNodes.length;j++)
					{
						var calCols = calRows.childNodes[j];
                        if (calCols.nodeType==1 && calCols.tagName=='TD')
                        {
                        	calRows.childNodes[j].innerHTML=calShowDate.getDate();
                            var calCell= calRows.childNodes[j],
                            calDisabled = (calOutOfRangeDisable &&
                                    (calShowDate < (new Date(calBaseYear,0,1))  ||
                                     calShowDate > (new Date(calBaseYear + calDropDownYears - 1,11,31))))?true:false;

                            for (var k=0;k<calDisabledDates.length;k++)
							{
								if ((typeof calDisabledDates[k]=='object') && 
                                    (calDisabledDates[k].constructor == Date) &&
                                    calShowDate.valueOf() == calDisabledDates[k].valueOf())
								{
  									calDisabled = true;
  								}
                                else
                                {
                                	if ((typeof calDisabledDates[k]=='object')  &&
                                         (calDisabledDates[k].constructor ==Array) &&  
                                          calShowDate.valueOf() >= calDisabledDates[k][0].valueOf() && 
                                          calShowDate.valueOf() <= calDisabledDates[k][1].valueOf())
									{
										calDisabled = true;
									}
                                }
							}

							if (calDisabled ||
                                 !calEnabledDay[j-1+(7*((i*calCells.
                                                          childNodes.
                                                          length)/6))] ||
                                 !calPassEnabledDay[(j-1+(7*(i*calCells.
                                                               childNodes.
                                                               length/6)))%7]
                                )
							{
								calRows.childNodes[j].onclick     = null;

								if (document.getElementById('calIFrame'))
                                {
                                	calRows.childNodes[j].onmouseover  = null;
                                	calRows.childNodes[j].onmouseout   = null;
                                }

                                 calCell.className=
                                    (calShowDate.getMonth()!=calCurMonth)
                                        ?'calCellsExMonthDisabled'
                                        :(calBlnFullInputDate &&
                                          calShowDate.toDateString()==
                                          calSeedDate.toDateString())
                                            ?'calInputDateDisabled'
                                            :(calShowDate.getDay()%6==0)
                                                ?'calCellsWeekendDisabled'
                                                :'calCellsDisabled';
                             }
                             else
                             {                             
                             	calRows.childNodes[j].onclick=calCellOutput;                             
								
								if (document.getElementById('calIFrame'))
                                {
                                    calRows.childNodes[j].onmouseover = calChangeClass;
                                     calRows.childNodes[j].onmouseout  = calChangeClass;
								}

                                 calCell.className=
                                     (calShowDate.getMonth()!=calCurMonth)
                                        ?'calCellsExMonth'
                                        :(calBlnFullInputDate &&
                                          calShowDate.toDateString()==
                                          calSeedDate.toDateString())
                                            ?'calInputDate'
                                            :(calShowDate.getDay()%6==0)
                                                ?'calCellsWeekend'
                                                :'calCells';
                                 
                                 // weekend, and not same month
                                 if (calCell.className == 'calCellsExMonth' && calShowDate.getDay()%6==0)
                                 {
                                	 calCell.className = 'calCellsExMonthWeekend';
                                 }
                             }
							
                            // today?
                            if (calShowDate.getDate()==calTodaysDate.getDate()
                           		 && calShowDate.getMonth()==calTodaysDate.getMonth()
                           		 && calShowDate.getYear()==calTodaysDate.getYear()
                           		 )
                            {
                            	calCell.className += ' calTodayDate';
                            }

							calShowDate.setDate(calShowDate.getDate()+1);
						}
					}
				}
			}
		}
		// Force a re-draw to prevent Opera's poor dynamic rendering
		// from leaving garbage in the calendar when the displayed
		// month is changed.
		//document.getElementById('calWidgetContainerDIV').style.display='none';
		//document.getElementById('calWidgetContainer').style.display='none';
		//document.getElementById('cal').style.display='none';
		//document.getElementById('calWidgetContainerDIV').style.display='';
		//document.getElementById('calWidgetContainer').style.display='';
		//document.getElementById('cal').style.display='';
		//CALPopup.hidePopup();
		
		if (isUXP())
		{
			CALPopup.showPopupBelow(gblCalSourceEle.id);
		}
		else
		{
			CALPopup.showPopup(gblCalSourceEle.id);
		}
		
		gblCalSourceEle.blur();
	}
	
    // **********************************************************************************
	// function calCancelButtonClick()
	// Hide the popup if the cancel button is clicked
	//
	// Parameters: None
	// **********************************************************************************
	function calCancelButtonClick()
	{	
		calTargetEle.value = pressedDate;
		calHide();	
		calTargetEle.focus();
	}
	
	
	// **********************************************************************************
	// function calOkButtonClick()
	// Accept the selected options if the OK button is clicked
	//
	// Parameters: None
	// **********************************************************************************
	function calOkButtonClick()
	{
	    //calTargetEle.value = currentlySelectedDate.calFormat(calDateOutputFormat);	
	    pressedDate =  calTargetEle.value;							
		calHide();
		calTargetEle.focus();
	}
	
	// *************************
	//  End of Function Library
	// *************************

	// ***************************
	// Start of Calendar structure
	// ***************************

	// **********************************************************************************
	// function createStructure()
	// Create the structure of the calendar, assign it to a div, add it to the document
	// and create the event listeners.
	//
	// Parameters: None
	// **********************************************************************************
	function createStructure()
	{
		calStructureCreated = true;

		var prevMonth = getDivButtonHTML(getLanguageLabel('GBLPREV'),'calHeadLeft','calShowMonth(-1)', 'calendarArrowLeft');
		var nextMonth = getDivButtonHTML(getLanguageLabel('GBLNEXT'),'calHeadRight','calShowMonth(1)', 'calendarArrowRight');
		if (RTL)
		{
			prevMonth = getDivButtonHTML(getLanguageLabel('GBLPREV'),'calHeadLeft','calShowMonth(-1)', 'calendarArrowRight');
			nextMonth = getDivButtonHTML(getLanguageLabel('GBLNEXT'),'calHeadRight','calShowMonth(1)', 'calendarArrowLeft');
		}

		// RTL?
		var strRTL = '';
		if (RTLOrg) strRTL = " wf_LTOR wf_RIGHT_ALIGN ";

   		var calHTML =  
     	"<table id='cal' class='cal'>" +
      	 "<tr class='cal'>" +
         "<td class='cal'>" +
           "<table class='calHead' id='calHead'" +
                    "cellspacing='0' cellpadding='0'>" +
              "</table>" +
            "</td>" +
          "</tr>" +
          "<tr class='cal'>" +
            "<td class='cal'>" +
              "<table cellspacing='0' class='calCells' align='center'>" +
                "<thead>" +
                "<tr class='calHead' >" +
                "<td class='calHead'>" +
                	prevMonth +
                "</td>" +
                "<td class='calHead' colspan='5'>" +
                   "<select id='calYears' class='calHead' " +
                           "onchange='calShowMonth(0);'>" +
                   "</select>" +
                "</td>" +
                "<td class='calHead'>" +
                	nextMonth +
                "</td>" +
                "</tr>" +
                  "<tr><td class='calWeekNumberHead' id='calWeek_' ></td>";

	    for (i=0;i<7;i++) calHTML += "<td class='calWeek' id='calWeekInit" + i + "'></td>";
	
	    calHTML += "</tr>" + "</thead>" + "<tbody id='calCells' " + "onClick='calStopPropagation(event);'>";
	
	    for (i=0;i<6;i++)
		{
	         calHTML +="<tr>" + "<td class='calWeekNo' id='calWeek_" + i + "'></td>";
	        
			for (j=0;j<7;j++)
			{
				 calHTML +="<td class='calCells' id='calCell_" + (j+(i*7)) + "'></td>";
			}
			 calHTML +="</tr>";
		}
	
	     calHTML += "</tbody>";
	
	    if ((new Date(calBaseYear + calDropDownYears, 11, 32)) > calTodaysDate &&
	        (new Date(calBaseYear, 0, 0)) < calTodaysDate)
	    {
	    	 calHTML +=
	                  "<tfoot class=''>" +
 	                    "<tr class=''>" +
	                      "<td colspan='8' class='calFootTd'>" + 
	                      "<select id='calMonths' class='calFoot' " +
	                              "onchange='calShowMonth(0);'>" +
	                      "</select>" +
	                      "</td>" +
	                    "</tr>" +
	                    "<tr class=''>" +
	                      "<td class='calFoot wf_LTOR' id='calFoot' colspan='8'>" +
	                      "</td>" +
		                    "</tr>" +
	                  "</tfoot>";
		}
	
	     calHTML += "</table>" +
	            "</td>" +
	          "</tr>" +
	        "</table>";
	    
		var strClass = 'widgetContainer';
		if (isUXP())
		{
			strClass = 'widgetContainerBelow';
		}
		
		if (asPopupDateWindow) strClass = 'widgetContainerAsPopup'; 
	    
	    popupHTML = 
	    '	<div class="' + strClass + '" id="calWidgetContainer" onkeydown="containerCalKeyDown(event)" onclick="containerCalOnClick(event)">'+
		'		<table class="widgetHeader" id = "calWidgetHeader" cellpadding="0" cellspacing="0" >'+
		'			<tr>'+
		'				<td class="labelText">'+
							getLanguageLabel('CALCAL') +
		'				</td>'+
		'			</tr>'+
		'		</table>'+
		'		<div class="widgetBody" id = "calWidgetBody">'+
							calHTML +
		'		</div>'+
		'	</div>';		 

		// Create a Divvie for the Popup	
		CALDiv = document.createElement('div');		
		CALDiv.id = "calWidgetContainerDIV";
		CALDiv.className = "widgetContainerDIV";
        CALDiv.style.zIndex=calZindex;
		CALDiv.innerHTML = popupHTML;
		document.body.appendChild(CALDiv);
				
		CALPopup = new PopupWindow("calWidgetContainerDIV");
		CALPopup.offsetX=0;
		CALPopup.offsetY=0;
		if (asPopupWindow==false)
		{
	    	CALPopup.autoHide();
	    }		
	
		//Add event listeners for all calendar related events
	    if (document.addEventListener)
		{
			//document.getElementById('calWidgetContainer').attachEvent('click',calUnAutoHide,false);
	        document.getElementById('calHeadLeft' ).addEventListener('click',calStopPropagation,false);
	        document.getElementById('calMonths'   ).addEventListener('click',calStopPropagation,false);
	        document.getElementById('calMonths'   ).addEventListener('change',calStopPropagation,false);
	        document.getElementById('calYears'    ).addEventListener('click',calStopPropagation,false);
	        document.getElementById('calYears'    ).addEventListener('change',calStopPropagation,false);
	        document.getElementById('calHeadRight').addEventListener('click',calStopPropagation,false);
		}
	//If this fails then attach the events
	    else    
	    {
	    //don't hide the container if we click on it
	    	document.getElementById('calWidgetContainer').attachEvent('onclick',calUnAutoHide);
			document.getElementById('calHeadLeft' ).attachEvent('onclick',calStopPropagation);
		    document.getElementById('calMonths'   ).attachEvent('onclick',calStopPropagation);
		    document.getElementById('calMonths'   ).attachEvent('onchange',calStopPropagation);
		    document.getElementById('calYears'    ).attachEvent('onclick',calStopPropagation);
		    document.getElementById('calYears'    ).attachEvent('onchange',calStopPropagation);
	    	document.getElementById('calHeadRight').attachEvent('onclick',calStopPropagation);
		}
 	}  
	// ***************************
	//  End of Calendar structure
	// ***************************
 	
 	// **********************************************************************************
	// function calUnAutoHide()
	// Correct AutoHide for Calendar Widget 
	//
	// Parameters: None	
	// ********************************************************************************** 	
	function calUnAutoHide()
	{
		//document.getElementById('calWidgetContainer').style.visibility='visible';
       	//document.getElementById('cal').style.visibility='visible';
	}
	
	// **********************************************************************************
	// function setCalendarButton(dteFieldId)
	// Append a calendar picker button
	//
	// Parameters:
	// dteFieldId: Input date value
	// **********************************************************************************	
	function setDateButton(dteFieldId)
	{
		var onclickAction = '';

		if (asPopupDateWindow)
		{
			onClickAction = 'calShowAsWindow(document.getElementById(\'' +  dteFieldId + '\'),document.getElementById(\'' +  dteFieldId + BUT_WIDGET + '\'));';
		}
		else
		{		
			if( isUXP())
			{
				onClickAction = 'calShow(document.getElementById(\'' +  dteFieldId + '\'),document.getElementById(\'' +  dteFieldId + BUT_WIDGET + 'Href\'));';			
			}
			else
			{
				onClickAction = 'calShow(document.getElementById(\'' +  dteFieldId + '\'),document.getElementById(\'' +  dteFieldId + BUT_WIDGET + '\'));';
			}
		}
		
		var hoverText = getLanguageLabel('CALDES');
		var labelText = dteFieldId + BUT_WIDGET;
		// Image for EQD only
		var imagePath = '/' + getWebAppName() + '/equation/images/calendar.gif';
		var classNames = 'calWidget';

		// WebFacing, then do not display the button if read-only
		var obj = document.getElementById(dteFieldId);
		if(isWebFacing() && obj.readOnly)
		{
			return;
		}
		
		// add the button
		var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath,classNames);

		// protected - then hide the widget 
		if (obj.readOnly)
		{
			disableAnchor(labelText + BUT_HREF, true);
			visibleObj(labelText, false);
		}
		
		return widgetButtonHTML;
	}
	
	// **********************************************************************************
	// function getUnitDate()
	// parse the unit date based on the date format and return it to the calling environment	
	// **********************************************************************************	
	function getUnitDate()
	{	
		var screenDate = getFrameCurrent().screenDate;		
		var processingDate = getFrameCurrent().unitDate;
		var year;
		var month;
		var date;
		var unitDate = new Date();		
		
		if(processingDate != null && processingDate != undefined && processingDate != "")
		{   
			var dateArr = screenDate.split(" ");
			
			date = processingDate.substr(processingDate.length - 2, 2);
			month = processingDate.substr(processingDate.length - 4, 2);			
			
			switch(dtFormat)
			{
				case "D" : 	//"DDMMYY" 						
							year = parseInt(dateArr[2], 10);
							break;
				case "M" : 	//"MMDDYY" 						
							year = parseInt(dateArr[2], 10);
							break;
				default  : 	//"YYMMDD"						
							year = parseInt(dateArr[0], 10);
							break;
			}
			
			unitDate.setFullYear(year, month - 1, date);			
		}
	
		return unitDate;
	}

	
	//**********************************************************************************
	// function containerCalKeyDown(e)
	// On key down event
	//
	// Parameters:
	// e - event
	//**********************************************************************************
	function containerCalKeyDown(e)
	{
		var keynum = rtvKeyboardKey(e);
		
		// escape
		if (keynum==27)
		{
			calCancelButtonClick()
			disableKeyboardKey(e);
			return false;
		}
		
		// enter
		else if (keynum==13)
		{
			disableKeyboardKey(e);
			return false;
		}
		
		// tab
		else if (keynum==9)
		{
			var shiftKey = isShiftKey(e);
			if (shiftKey && e.srcElement == document.getElementById('calYears'))
			{
				document.getElementById('calMonths').focus();
				disableKeyboardKey(e);
			}
			else if (!shiftKey && e.srcElement == document.getElementById('calMonths'))
			{
				document.getElementById('calYears').focus();
				disableKeyboardKey(e);
			}
		}
	}
	
	//**********************************************************************************
	// function containerCalOnClick(e)
	// On click down event
	//
	// Parameters:
	// e - event
	//**********************************************************************************
	function containerCalOnClick(e)
	{
		disableKeyboardKey(e);
		return false;
	}
	