cd lab0

# -> 1
echo ' |- task #[1] -| '
(wc -c *o **/*o | sort -n) 2>>1
echo ' -| compete |-\n'

# -> 2
echo ' |- task #[2] -| '
(ls -lS | tail -n2) 2>>./tmp/null
echo ' -| compete |-\n'

# -> 3
echo ' |- task #[3] -| '
(cat s* **/s* | sort -r) 2>&1
echo ' -| compete |-\n'

# -> 4
echo ' |- task #[4] -| '
(wc -c s* **/s* | sort -n) 2>&1
echo ' -| compete |-\n'

# -> 5
echo ' |- task #[5] -| '
(cat sandslash4 -n | grep -v 'e$') 2>&1
echo ' -| compete |-\n'

# -> 6
echo ' |- task #[6] -| '
(ls -lt | grep e$ | head -n2) 2>&1
echo ' -| compete |-\n'

