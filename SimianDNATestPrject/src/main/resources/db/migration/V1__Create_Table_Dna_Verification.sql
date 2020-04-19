CREATE TABLE IF NOT EXISTS  `simian`.`dna_verification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dna_code` varchar(2000) NOT NULL,
  `dna_side_length` int(11) NOT NULL,
  `is_simian` bit(1) NOT NULL,
  `hash_code` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_constraint_dna_verification_dna_code` (`dna_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;