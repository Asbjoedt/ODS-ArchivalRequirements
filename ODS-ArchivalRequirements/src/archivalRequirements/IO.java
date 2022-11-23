package archivalRequirements;

import org.apache.commons.io.*;
import java.io.*;

public class IO {
    public Pair<String, String> CheckIO(String input_filepath, String output_filepath) throws IOException {
        String filepath = "";

        // Check if input filepath exists
        File input_file = new File(input_filepath);
        if (!input_file.exists() && !input_file.isDirectory()) {
            throw new IOException("Input file does not exist");
        }

        // Check if output directory exists
        String parent = input_file.getParent();
        File directory = new File(parent);
        if (!directory.exists()) {
            throw new IOException("Output directory does not exist");
        }

        // Check if output filepath has .ods file format extension
        File output_file = new File(output_filepath);
        String output_extension = FilenameUtils.getExtension(output_filepath).toLowerCase();
        if (output_extension != "ods" && !output_file.isDirectory()) {
            throw new IOException("Output filepath does not have accepted file format extension .ods");
        }

        // Check for file protection and corruption
        boolean readable = input_file.canRead();
        boolean writeable = input_file.canWrite();
        if (!readable) {
            throw new IOException("File cannot be processed e.g. has password protection, is corrupt");
        }
        if (!writeable) {
            throw new IOException("File cannot be processed e.g. has password protection, is corrupt");
        }

        // Check for accepted file format extensions and copy file
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();
        switch (input_extension) {

            case "ods":
                // Copy file, if output filepath is set
                if (output_filepath != null && output_filepath == input_filepath) {
                    filepath = CopyFile(input_filepath, output_filepath);
                }
                // Else use input filepath for operations
                else if (output_filepath == null || output_filepath == input_filepath) {
                    filepath = input_filepath;
                }
                return filepath;

            case "fods":
            case "ots":
                // Copy file, if output filepath is set
                if (output_filepath != null && output_filepath == input_filepath) {
                    filepath = CopyFile(input_filepath, output_filepath);
                }
                // Else use input filepath for operations
                else if (output_filepath == null || output_filepath == input_filepath) {
                    filepath = FilenameUtils.getFullPath(input_filepath) + FilenameUtils.getBaseName(input_filepath) + ".ods";
                }
                return filepath;

            case "xls":
            case "xla":
            case "xlt":
            case "xlsx":
            case "xlsm":
            case "xltm":
            case "xltx":
            case "xlam":
                // Copy file, if output filepath is set
                if (output_filepath != null) {
                    filepath = CopyFile(input_filepath, output_filepath);
                }
                // Else use input filepath for operations
                else if (output_filepath == null) {
                    filepath = FilenameUtils.getFullPath(input_filepath) + FilenameUtils.getBaseName(input_filepath) + ".ods";
                }
                return filepath;

            default:
                throw new IOException("Input filepath does not have an accepted file format extension");
        }
    }

    // Method for copying a file
    private static String CopyFile(String input_filepath, String output_filepath) throws IOException {
        File input_file = new File(input_filepath);
        File output_file = new File(output_filepath);
        FileUtils.copyFile(input_file, output_file);
        return output_filepath;
    }

    // Method for deleting input file
    public void DeleteInputFile(String input_filepath) {
        File del = new File(input_filepath);
        boolean success = del.delete();
        if (success == true) {
            System.out.println("Input file was deleted");
        } else {
            System.out.println("Input file was NOT deleted");
        }
    }
}