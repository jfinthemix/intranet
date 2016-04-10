<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String mens=(String)request.getParameter("mensaje");
%>

<html>
<body bgcolor="#000000" scroll="no">

<script>
<!-- // Hide

var isDOM = false, isNS4 = false;
if (document.all) var isDOM = true, docObj = 'document.all.', styObj = '.style';
else if (document.layers) var isNS4 = true, docObj = 'document.', styObj = '';

var quotes = new Array(); c = 0;
quotes[c] = '<%= mens %>'; c++;


var visQuotes = 1; // Maximum onscreen at once.

var sDivSty = new Array(visQuotes);
var sDivRef = new Array(visQuotes);
var speed = new Array(visQuotes);

//  Left: Low speed colours (lighter) .... Right: High speed colours (darker).
var colours = new Array('FABD41');


function checkDivs()
{
 for (i = 0; i < visQuotes; i++)
 {
  // If it's moved offscreen to the left (or starting), set things in motion...
  if (parseInt(sDivSty[i].left) < (0 - (isDOM ? sDivRef[i].clientWidth : sDivSty[i].clip.width)))
  {
   speed[i] =9; // Varies: 8 to 63.
   // Off to the right it goes.
   sDivSty[i].left = (isDOM ? document.body.clientWidth : window.innerWidth) + Math.random() * 10;
   // Write a quote in a colour that depends on the speed.

/*  Stylesheets - guess which browser has bugs :)
 *
 *  divText = '<nobr><span style="font: ' + speed[i] + 'px Arial, Helvetica; ' +
 *   'color: #' + colours[Math.floor(speed[i] / 8) - 1] + '">' +
 *   quotes[Math.floor(Math.random() * quotes.length)] + '</span></nobr>';
 */

   fontSize = 40;
   divText = '<nobr><strong><font face="Arial, Helvetica,steelwolfuntitled,Steamer,"  size="' + fontSize + '" color="#FFAA31">' + quotes[Math.floor(Math.random() * quotes.length)] +
    '</font></strong></nobr>';

   if (isDOM) sDivRef[i].innerHTML = divText;
   if (isNS4)
   {
    sDivRef[i].document.write(divText);
    sDivRef[i].document.close();
   }
   // Position and layer it according to its speed (faster = higher).
   sDivSty[i].zIndex = speed[i];
   topMax = 50<!--(isDOM ? document.body.clientHeight : innerHeight) - speed[i];-->
   sDivSty[i].top = topMax ;<!--* Math.random();-->
  }
  // All items: Keep 'em moving left.
  sDivSty[i].left = parseInt(sDivSty[i].left) - (speed[i] /2);
 }
}


function initDivs()
{
 for (i = 0; i < visQuotes; i++)
 {
  divID = 'sDiv' + i.toString();
  if (isDOM) document.write('<div id="' + divID + '" style="position: absolute; left: -1000">&nbsp;</div>');
  // Have to use layers, divs are buggy as..... in NS. Again.
  if (isNS4) document.write('<layer id="' + divID + '" left="-1000">&nbsp;</layer>');
  sDivRef[i] = eval(docObj + 'sDiv' + i);
  sDivSty[i] = eval(docObj + 'sDiv' + i + styObj);
 }

 setInterval('checkDivs()', 30);
}

if (isDOM || isNS4) initDivs();

// End Hide -->
</script>
</body>
</html>
