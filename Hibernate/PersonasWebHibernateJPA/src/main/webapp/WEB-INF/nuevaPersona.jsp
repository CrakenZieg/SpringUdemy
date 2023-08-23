<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar persona</title>
    </head>
    <body>        
        <fieldset>
            <legend>Agregar persona:</legend>
            <form name="form2" action="/PersonasWebHibernateJPA/ServletNuevaPersona" method="post">
            <input type="hidden" name="idAlumno" />
            <label for="nombre">Nombre:</label><br>
            <input type="text" name="nombre" id="nombre" ><br>
            <label for="apellido">Apellido:</label><br>
            <input type="text" name="apellido" id="apellido" ><br>
            <label for="email">Email:</label><br>
            <input type="text" name="email" id="email" ><br>
            <label for="telefono">Telefono:</label><br>
            <input type="text" name="telefono" id="telefono" ><br> 
            <input type="submit" name="Submit" value="Agregar">
            <input type="submit" name="Submit" value="Cancelar">
            </form>
        </fieldset>        
    </body>
</html>
