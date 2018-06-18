<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
	<ul>
		<li><a href="userhome.jsp">Home</a></li>
		<div class="dropdown">
			<li class="dropbtn"><a>Task</a>
				<div class="dropdown-content">
					<a href=""><i class="fa fa-list"></i> List tasks</a> 
					<a href=""><i class="fa fa-check-square-o"></i> Change status</a>
					<a href=""><i class="fa fa-list-alt"></i> Others tasks</a>
				</div></li>
		</div>
		<div class="dropdown">
			<li class="dropbtn"><a>Message</a>
				<div class="dropdown-content">
					<a href="inbox.jsp"><i class="fa fa-envelope-o"></i> Inbox</a> 
					<a href="trash.jsp"><i class="fa fa-trash-o"></i> Trash</a>
				</div></li>
		</div>
		<li class="right" onclick="logout('')"><a href="#logout">Logout</a></li>
	</ul>