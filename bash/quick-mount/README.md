## Quick Mount - Tyler Hippard
*fstab or f-stab?*

Very simple bash script that takes a device path, and a mountable folder as its arguments.
The folder is created if it doesn't exist, the device is mounted to that folder, and the mount is appended to /etc/fstab using the partition's UUID and partition type.
It'll then list the last few lines of /etc/fstab for you to ensure that the script didn't mess something up.

Requires: Some flavour of bash, blkid, access to sudo

Usage:
  1. Download/copy-paste the script and make it executable.
  2. Run it like so:
<pre> sudo ./quick_mount [device path] [mounth path] </pre>
You could also add it to your .bashrc or path and use it anywhere, if you'd like.
