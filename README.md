# Cryptography_Code_and_explanation
# Custom Cryptographic Algorithms

This repository contains custom implementations of various cryptographic algorithms such as DES, Bitmap Cipher, Monoalphabetic Cipher, and Polyalphabetic Cipher. A text file with input is processed and encrypted using these algorithms.

## Algorithms Implemented
1. **DES (Data Encryption Standard)**: A symmetric-key algorithm used for the encryption of digital data.
2. **Bitmap Cipher**: A custom cipher based on bitmap encoding.
3. **Monoalphabetic Cipher**: A substitution cipher where each letter of the plaintext is mapped to a fixed corresponding letter of the ciphertext.
4. **Polyalphabetic Cipher**: A cipher based on multiple substitution alphabets to encrypt data.

## Features
- **Text File Encryption**: Provide a text file with your input, and the implemented algorithms will process and encrypt the contents.
- **Custom Algorithm Logic**: The encryption logic for each algorithm is custom-built, following cryptographic principles.
  
## Usage

### Prerequisites
- Ensure you have a JDK installed (version 8 or higher).
- Clone the repository to your local machine:
    ```bash
    git clone https://github.com/your-username/crypto-algorithms.git
    cd crypto-algorithms
    ```

### How to Run
1. Compile the Java files:
    ```bash
    javac -d bin src/*.java
    ```
2. Run the encryption program:
    ```bash
    java -cp bin Main input.txt
    ```
    - `input.txt` should be the file containing the text you wish to encrypt.

### Example
To encrypt a file `example.txt` using DES:
```bash
java -cp bin Main example.txt DES

java -cp bin Main input.txt MONO


# This `README.md` provides an overview of your project, instructions on how to run it, and details about the algorithms implemented. Let me know if you need any further customization!

