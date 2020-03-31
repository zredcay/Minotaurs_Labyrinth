<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<html>
	<head>
		<title>Minotaur's Labyrinth</title>
		<style type="text/css">
		
		<%--set error text to red--%>
		.error {
			color: white;
		}

		body {
  			background-image: url('https://images.pexels.com/photos/235985/pexels-photo-235985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
  			background-size: cover;
 			background-repeat: no-repeat;
		}
		div.textbox{
			margin: 0;
    		position: absolute;
    		top: 90%;
    		left: 50%;
    		margin-right: -50%;
    		transform: translate(-50%, -50%);
    		text-align: center;
    		border-style: solid;
    		border-color: brown;
    		background-color: brown;
		}
		div.gameText{
			margin: 0;
    		position: absolute;
    		top: 80%;
    		left: 50%;
    		margin-right: -50%;
    		transform: translate(-50%, -50%);
    		text-align: center;
    		overflow:auto;
    		height: 1000px;
		}
		</style>		
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
		<div class="gameText">
		<table>
		<c:forEach items="${outputstrings}" var="strings">
			        <tr>
			            <td name="output">${strings}</td>	
			             <input name="test" type="hidden" value="${strings}" />         
			        </tr>
			    </c:forEach>
		</table>
		</div>
		
		<div class="textbox">
		<table class = "textbox">
			<tr>
				<td><input name="textbox" autocomplete="off" type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
			</tr>
			<input name="location" type="hidden" value="${game.roomPosition}" />
			<input name="playerHP" type="hidden" value="${game.playerHP}" />
			<input name="enemyIsDead" type="hidden" value="${game.enemyDead}" />
			<input name="villagerIsDead" type="hidden" value="${game.villagerDead}" />
			<input name="enemyHP" type="hidden" value="${game.enemyHP}" />
			<input name="villagerHP" type="hidden" value="${game.villagerHP}" />

		</table>
		</div>
	</form>
	</body>
</html>