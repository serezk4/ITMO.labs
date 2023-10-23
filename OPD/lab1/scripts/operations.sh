cd lab0

# -> 1
echo ' |- task #[1] -| '
(wc -c *o **/*o | sort -n) 2>>1
echo -e ' -| compete |-\n'

# -> 2
echo ' |- task #[2] -| '
(ls -lS | tail -n2) 2>>./dev/null
echo -e ' -| compete |-\n'

# -> 3
echo ' |- task #[3] -| '
cat s* **/s* #todo # | sort -r
echo -e ' -| compete |-\n'

# -> 4
echo ' |- task #[4] -| '
wc -c s* **/s* | sort -n
echo -e ' -| compete |-\n'

# -> 5
echo ' |- task #[5] -| '
cat sandslash4 -n | grep -v 'e$'
echo -e ' -| compete |-\n'

# -> 6
echo ' |- task #[6] -| '
ls -lt | grep e$ | head -n2
echo -e ' -| compete |-\n'

