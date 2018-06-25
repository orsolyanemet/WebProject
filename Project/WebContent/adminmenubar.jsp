<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<ul>
		<li><a href="adminhome.jsp">Home</a></li>
		<div class="dropdown">
			<li class="dropbtn"><a>Event</a>
				<div class="dropdown-content">
					<a href="event.jsp"><i class="fa fa-plus-square"></i> Add event</a>
				</div></li>
		</div>
		<div class="dropdown">
			<li class="dropbtn"><a>Task</a>
				<div class="dropdown-content">
					<a href="addtask.jsp"><i class="fa fa-plus-square"></i> Add task</a> 
					<a href="eventtasks.jsp"><i class="fa fa-list"></i> List tasks</a>
				</div></li>
		</div>
		<div class="dropdown">
			<li class="dropbtn"><a>Organizer</a>
				<div class="dropdown-content">
					<a href="neworganizer.jsp"><i class="fa fa-user-plus"></i> Add organizer</a> 
					<a href=""><i class="fa fa-edit"></i> Edit organizer</a>
					<a href="deleteuser.jsp"><i class="fa fa-user-times"></i>  Delete organizer</a>
				</div></li>
		</div>
		<div class="dropdown">
			<li class="dropbtn"><a>Message</a>
				<div class="dropdown-content">
					<a href="composemessage.jsp"><i class="fa fa-edit"></i> Compose</a> 
				</div></li>
		</div>
		<li class="right" onclick="logout('')"><a href="#logout">Logout</a></li>
	</ul>