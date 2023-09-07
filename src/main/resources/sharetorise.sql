-- Create a table for fundraisers
CREATE TABLE fundraiser (
    id INT AUTO_INCREMENT, -- Unique identifier for each fundraiser
    title VARCHAR(30) NOT NULL, -- Title of the fundraiser (max 30 characters)
    description VARCHAR(300) NOT NULL, -- Description of the fundraiser (max 300 characters)
    funding_goal DOUBLE NOT NULL, -- The goal amount of funding for the fundraiser
    image_url VARCHAR(200) NOT NULL, -- URL of the fundraiser's image
    fund_ending_date DATE NOT NULL, -- Date when the fundraising period ends
    PRIMARY KEY(id), -- Primary key constraint for the id column
    CHECK(funding_goal >= 2000) -- Check constraint to ensure funding_goal is at least 2000
);

-- Create a table for certificates
CREATE TABLE certificates (
    id INT AUTO_INCREMENT, -- Unique identifier for each certificate
    fundraiser_id INT, -- Foreign key referencing the fundraiser associated with this certificate
    cert_number VARCHAR(50), -- Certificate number (max 50 characters)
    cert_image_url VARCHAR(300), -- URL of the certificate's image
    PRIMARY KEY(id), -- Primary key constraint for the id column
    FOREIGN KEY(fundraiser_id) REFERENCES fundraiser(id) -- Foreign key constraint referencing fundraiser table
);

-- Insert a new fundraiser record
"INSERT INTO fundraiser (title, description, funding_goal, fund_ending_date, image_url) VALUES (?, ?, ?, ?, ?)"

-- Update an existing fundraiser record
"UPDATE fundraiser SET title = ?, description = ?, funding_goal = ?, fund_ending_date = ?, image_url = ? WHERE id = ?"

-- Insert a new certificate record
"INSERT INTO certificates (fundraiser_id, cert_number, cert_image_url) VALUES (?, ?, ?)"

-- Update an existing certificate record
"UPDATE certificates SET cert_number = ?, cert_image_url = ? WHERE fundraiser_id = ?"

-- Select specific columns from the fundraiser table
"SELECT id, title, description, funding_goal, image_url, fund_ending_date FROM fundraiser"

-- Delete a fundraiser record based on its id
"DELETE FROM fundraiser WHERE id = ?"

-- Delete certificates associated with a specific fundraiser id
"DELETE FROM certificates WHERE fundraiser_id = ?"
