SELECT 
    user_id, username, COALESCE(phoneno,email,"Contact not available") AS Contact
FROM 
    user
WHERE 
    dob IS NOT NULL AND MONTH(dob)=8
ORDER BY 
    username ASC;