SELECT 
    user_id,
    username,
    CASE 
        WHEN address='New York' THEN CONCAT(SUBSTRING(email,1,INSTR(email,'@')-1),'@yahoo.com')
        ELSE email
    END AS email_details
FROM 
    user 
ORDER BY 
    username ASC;
    