# ex_04-1

# Learning Perl on Win32 Systems, Exercise 4.1



print "What temperature is it? ";

chomp($temperature = <STDIN>);

if ($temperature > 72) {

  print "Too hot!\n";

} else {

  print "Too cold!\n";

}

