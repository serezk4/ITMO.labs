echo 'creating files...'
sh create_files.sh
tree lab0

echo 'filling files...'
sh fill_files.sh
tree lab0

echo 'setting rules...'
sh set_rules.sh
tree lab0

echo 'creating links...'
sh create_links.sh
tree lab0

echo 'doing operations...'
sh operations.sh
tree lab0

echo 'removing files'
sh remove_files.sh
tree lab0

#tree
