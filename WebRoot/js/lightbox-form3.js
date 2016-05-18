

function gradient3(id, level)
{
	var box = document.getElementById(id);
	box.style.opacity = level;
	box.style.MozOpacity = level;
	box.style.KhtmlOpacity = level;
	box.style.filter = "alpha(opacity=" + level * 100 + ")";
	box.style.display="block";
	return;
}


function fadein3(id) 
{
	var level = 0;
	while(level <= 1)
	{
		setTimeout( "gradient3('" + id + "'," + level + ")", (level* 1000) + 10);
		level += 0.01;
	}
}


// Open the lightbox

/*
function openbox(formtitle, fadin)
{
  var box = document.getElementById('box'); 
  document.getElementById('shadowing').style.display='block';

  var btitle = document.getElementById('boxtitle');
  btitle.innerHTML = formtitle;
  
  if(fadin)
  {
	 gradient("box", 0);
	 fadein("box");
  }
  else
  { 	
    box.style.display='block';
  }  	
}*/

function openboxx(formtitle, fadin,departmentID,salaryItemID)
{
  var box = document.getElementById('box3'); 
  document.getElementById('shadowing3').style.display='block';

  var btitle = document.getElementById('boxtitle3');
  btitle.innerHTML = formtitle;
  
  if(fadin)
  {
	 gradient3("box3", 0);
	 fadein3("box3");
  }
  else
  { 	
    box.style.display='block';
  }  
  document.getElementById("salaryItemID2").value=salaryItemID;
  document.getElementById("departmentID2").value=departmentID;
}


// Close the lightbox

function closeboxx()
{
   document.getElementById('box3').style.display='none';
   document.getElementById('shadowing3').style.display='none';
}



