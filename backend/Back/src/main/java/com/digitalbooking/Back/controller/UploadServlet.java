/*package com.digitalbooking.Back.controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils; // Para obtener la extensión del archivo
import org.apache.commons.io.IOUtils; // Para convertir el contenido del archivo a un array de bytes
import org.apache.tika.Tika; // Para validar el tipo MIME del archivo
    @WebServlet("/upload")
    @MultipartConfig(maxFileSize = 1048576L)
    public class UploadServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String uploadDir = "C:/uploads"; // Directorio donde se guardará la imagen cargada
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir(); // Crea el directorio si no existe
            }

            Part filePart = request.getPart("file"); // Obtiene el archivo cargado desde la solicitud HTTP
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el nombre del archivo
            InputStream fileContent = filePart.getInputStream(); // Obtiene el contenido del archivo

            // Validar el tipo de archivo
            Tika tika = new Tika();
            String mimeType = tika.detect(fileContent); // Detecta el tipo MIME del archivo
            if (!mimeType.startsWith("image/")) {
                response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Solo se permiten imágenes"); // Envía un error si el archivo no es una imagen
                return;
            }

            // Validar el tamaño del archivo
            if (filePart.getSize() > 1048576L) {
                response.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "El archivo es demasiado grande"); // Envía un error si el archivo es demasiado grande
                return;
            }

            // Evitar la ejecución de código malicioso
            byte[] fileContentBytes = IOUtils.toByteArray(fileContent);
            String extension = FilenameUtils.getExtension(fileName); // Obtiene la extensión del archivo
            if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Solo se permiten imágenes en formato JPG, JPEG y PNG"); // Envía un error si el archivo no es una imagen en formato permitido
                return;
            }
            if (!Arrays.equals(fileContentBytes, Files.readAllBytes(Paths.get(uploadDir, fileName)))) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El archivo es inválido"); // Envía un error si el archivo es inválido
                return;
            }

            Path filePath = Paths.get(uploadDir, fileName); // Crea la ruta donde se guardará el archivo
            Files.copy(fileContent, filePath); // Guarda el archivo en la ruta especificada

            response.sendRedirect("success.html"); // Redirige a una página de éxito
        }

    }
*/