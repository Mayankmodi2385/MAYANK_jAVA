import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JsonMD5Hasher {

    public static JsonObject jsonObject;
    
        public static void main(String[] args) {
            try {
                
    
                // Parse the JSON string using Gson
                //final JsonObject jsonObject = JsonParser.parseString(content).getAsJsonobject();
               
                // Extract first name and roll number
                JsonObject student = jsonObject.getAsJsonObject("student");
            String firstName = student.get("first_name").toString().toLowerCase().replace(" ", "");
            String rollNumber = student.get("roll_number").toString().toLowerCase().replace(" ", "");
           
            // Concatenate first name and roll number
            String concatenatedString = firstName + rollNumber;
           
            // Generate the MD5 hash of the concatenated string
            String md5Hash = generateMD5(concatenatedString);
           
            // Write the MD5 hash to output.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write(md5Hash);
            }
           
            // Print the result for verification
            System.out.println("MD5 Hash: " + md5Hash);
           
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Method to generate MD5 hash
    public static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
       
        // Convert the byte array to a hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
       
        return hexString.toString();
    }
}