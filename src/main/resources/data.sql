-- ═══════════════════════════════════════════════════════════════
--  data.sql — Seeds the animals table with sample data
--  Runs automatically on startup (spring.sql.init.mode=always)
--  Uses INSERT ... WHERE NOT EXISTS so it won't duplicate on restart
-- ═══════════════════════════════════════════════════════════════

INSERT INTO animals (
    name, type, breed, age, gender, location, image, description,
    full_description, personality, weight, vaccinated, spayed_neutered,
    good_with_kids, good_with_pets, trained, medical_needs, adoption_fee, status, created_at
)
SELECT * FROM (VALUES

  ('Max', 'Dog', 'Golden Retriever', '3 years', 'Male', 'Los Angeles, CA',
   'https://images.unsplash.com/photo-1648799834307-97650bbf7298?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Friendly and energetic Golden Retriever who loves outdoor adventures.',
   'Max is an incredibly loving and gentle Golden Retriever who came to us after his owner could no longer care for him. Despite his circumstances, he remained one of the most affectionate dogs we have ever had. He thrives on human companionship and loves nothing more than going on long hikes, playing fetch, and cuddling on the couch.',
   'Friendly, Energetic, Loyal, Gentle, Playful',
   '65 lbs', true, true, 'Yes', 'Yes - loves other dogs', 'House trained, knows basic commands', 'None', '$350',
   'AVAILABLE', NOW()),

  ('Luna', 'Cat', 'Orange Tabby', '2 years', 'Female', 'New York, NY',
   'https://images.unsplash.com/photo-1726263855367-5e8d08046f98?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Gentle and affectionate tabby who loves sunny spots and cuddles.',
   'Luna is a sweet and gentle orange tabby who was rescued from a hoarding situation. She was initially shy but has blossomed into one of the most affectionate cats in our shelter. She loves to curl up in sunny spots, play with feather toys, and purr loudly when being petted.',
   'Gentle, Affectionate, Curious, Calm, Independent',
   '9 lbs', true, true, 'Yes - with older children', 'Yes - with calm dogs', 'Litter trained', 'None', '$150',
   'AVAILABLE', NOW()),

  ('Buddy', 'Dog', 'Beagle', '1 year', 'Male', 'Chicago, IL',
   'https://images.unsplash.com/photo-1659693261741-ac36585bc6a2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Playful young Beagle puppy bursting with energy and curiosity.',
   'Buddy is a lively and curious young Beagle who was found wandering the streets at just 3 months old. He is incredibly playful and loves to explore every corner of the yard. Buddy would thrive in an active family who can keep up with his energy and curiosity.',
   'Curious, Energetic, Playful, Mischievous, Sweet',
   '22 lbs', true, true, 'Yes - great with kids', 'Yes - loves to play', 'Learning basic commands', 'None', '$250',
   'AVAILABLE', NOW()),

  ('Milo', 'Cat', 'Siamese', '4 years', 'Male', 'Houston, TX',
   'https://images.unsplash.com/photo-1669891732707-4beb1437679a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Vocal and intelligent Siamese who loves interactive toys and conversation.',
   'Milo is a strikingly beautiful Siamese cat with piercing blue eyes and a personality to match. He is highly intelligent and loves interactive toys, puzzle feeders, and having long conversations with his humans. He was surrendered when his owner moved abroad and is looking for someone who will appreciate his wit.',
   'Intelligent, Vocal, Affectionate, Playful, Social',
   '11 lbs', true, true, 'Yes', 'Prefers to be only pet', 'Litter trained', 'None', '$175',
   'AVAILABLE', NOW()),

  ('Bella', 'Dog', 'Labrador Mix', '5 years', 'Female', 'Phoenix, AZ',
   'https://images.unsplash.com/photo-1721227319522-553452acea54?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Calm and loyal Lab mix who is wonderful with children and the elderly.',
   'Bella is a calm and devoted Labrador mix who spent her first years as a therapy dog before her handler retired. She is exceptionally well-trained, highly intuitive, and has a natural gift for comforting people. Bella would be perfect for a family with children or someone looking for an emotionally supportive companion.',
   'Calm, Loyal, Gentle, Intuitive, Devoted',
   '55 lbs', true, true, 'Yes - exceptional with kids', 'Yes', 'Fully trained - therapy certified', 'None', '$300',
   'AVAILABLE', NOW()),

  ('Oliver', 'Cat', 'British Shorthair', '3 years', 'Male', 'Seattle, WA',
   'https://images.unsplash.com/photo-1694987009537-890c935ae429?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Laid-back British Shorthair who enjoys peace, quiet, and cozy evenings.',
   'Oliver is a plush, round-faced British Shorthair with a wonderfully easygoing personality. He is the definition of a lap cat — happy to spend his days in a sunny window or snuggled next to you on the couch. Oliver would be perfect for a quieter home or apartment.',
   'Laid-back, Gentle, Quiet, Affectionate, Independent',
   '13 lbs', true, true, 'Yes - with calm older children', 'Yes - with calm cats', 'Litter trained', 'None', '$200',
   'AVAILABLE', NOW()),

  ('Rocky', 'Dog', 'German Shepherd', '2 years', 'Male', 'Denver, CO',
   'https://images.unsplash.com/photo-1571006473030-0036fa5e5ca2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Smart and athletic German Shepherd who excels at agility and obedience.',
   'Rocky is a highly intelligent and athletic German Shepherd who was donated by a training program after demonstrating exceptional aptitude. He knows over 30 commands, excels at agility courses, and craves mental and physical challenges. Rocky needs an experienced handler who can match his drive and intellect.',
   'Intelligent, Athletic, Alert, Loyal, Driven',
   '75 lbs', true, true, 'Yes - with supervision', 'With gradual introduction', 'Advanced obedience trained', 'None', '$400',
   'AVAILABLE', NOW()),

  ('Daisy', 'Dog', 'Poodle Mix', '6 years', 'Female', 'Miami, FL',
   'https://images.unsplash.com/photo-1701955400960-44d0f2af892e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080',
   'Sweet and hypoallergenic Poodle mix who is gentle with everyone she meets.',
   'Daisy is a charming and gentle Poodle mix who is virtually hypoallergenic — great news for allergy sufferers! She is sweet-natured, gets along with everyone, and has a quiet, loving temperament. Daisy was surrendered when her owner entered a care facility and is looking for a calm, loving forever home.',
   'Sweet, Gentle, Quiet, Loving, Easy-going',
   '20 lbs', true, true, 'Yes', 'Yes', 'House trained, knows basic commands', 'None', '$275',
   'AVAILABLE', NOW())

) AS v(name, type, breed, age, gender, location, image, description,
       full_description, personality, weight, vaccinated, spayed_neutered,
       good_with_kids, good_with_pets, trained, medical_needs, adoption_fee, status, created_at)

WHERE NOT EXISTS (SELECT 1 FROM animals LIMIT 1);
