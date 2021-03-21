# Quick mount - Tyler Hippard
if [[ $# -eq 0 || $# -eq 1 ]
  then
    echo "Usage: sudo ./quick_mount [device path] [mount path]";
    exit 1;
fi

echo "Attempting to make directory at $2";
mkdir $2;

mount $1 $2;
echo "$1 mounted on $2. Appending configuration to /etc/fstab.";

# Get partition UUID
uuid=`blkid -s UUID -o value $1`;
# Get partition fs type
fs=`blkid -s TYPE -o value $1`;
# Append to fstab
echo "UUID=$uuid $2 $fs defaults 0 0" >> /etc/fstab;
printf "\nDisplaying last 3 lines of /etc/fstab...\n";
tail -3 /etc/fstab;
printf "\n### Verify fstab is configured properly! ###\n";
