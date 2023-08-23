<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
    </head>
    <body>        
        <fieldset>
            <legend>Editar: ${persona.id}</legend>
            <form name="form2" action="/PersonasWebHibernateJPA/ServletFormulario" method="post">
            <input type="hidden" name="idAlumno" value="${persona.id}"/>
            <label for="nombre">Nombre:</label><br>
            <input type="text" name="nombre" id="nombre" value="${persona.nombre}"><br>
            <label for="apellido">Apellido:</label><br>
            <input type="text" name="apellido" id="apellido" value="${persona.apellido}"><br>
            <label for="email">Email:</label><br>
            <input type="text" name="email" id="email" value="${persona.email}"><br>
            <label for="telefono">Telefono:</label><br>
            <input type="text" name="telefono" id="telefono" value="${persona.telefono}"><br> 
            <input type="submit" name="Modificar" value="Modificar">
            <input type="submit" name="Eliminar" value="Eliminar">
            </form>
        </fieldset>        
    </body>
</html>
