#!/usr/local/bin/perl5

##++
##    Sprite v3.1
##    Last modified: June 18, 1996
##
##    Copyright (c) 1995, 1996
##    Shishir Gundavaram and O'Reilly & Associates
##    All Rights Reserved
##
##    E-Mail: shishir@ora.com
##
##    Permission to use, copy, modify and distribute is hereby granted,
##    providing  that  no charges are involved and the above  copyright
##    notice and this permission appear in all copies and in supporting
##    documentation. Requests for other distribution  rights, including
##    incorporation in commercial  products,  such as  books,  magazine
##    articles, or CD-ROMS should be made to the authors.
##
##    This  program  is distributed in the hope that it will be useful,
##    but WITHOUT ANY WARRANTY;  without  even  the implied warranty of
##    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
##--

#############################################################################

=head1 NAME

Sprite - Perl 5.0 module to manipulate text delimited databases.

=head1 SYNOPSIS

    use Sprite;

    $rdb = new Sprite ();

    $rdb->set_delimiter ("Read", "::");
    $rdb->set_delimiter ("Write", "::");

    $rdb->set_os ("UNIX");

    $rdb->sql (<<Query);
        .
        .
        .
    Query

    $rdb->close ();
    $rdb->close ($database);

=head1 DESCRIPTION

Here is a simple database where the fields are delimted by commas:

    Player,Years,Points,Rebounds,Assists,Championships
    ...                                                         
    Larry Joe Bird,12,28,10,7,3
    Michael Jordan,10,33,6,5,3
    Earvin Magic Johnson,12,22,7,12,5
    ...

I<Note:> The first line must contain the field names (case sensitive).

=head1 Supported SQL Commands

Here are a list of the SQL commands that are supported by Sprite:

=over 5

=item I<select> - retrieves records that match specified criteria:

    select col1 [,col2] from database 
        where (cond1 OPERATOR value1) 
        [and|or cond2 OPERATOR value2 ...] 

The '*' operator can be used to select all columns.

The I<database> is simply the file that contains the data. 
If the file is not in the current directory, the path must 
be specified. 

Sprite does I<not> support multiple tables (or commonly knows
as "joins").

Valid column names can be used where [cond1..n] and 
[value1..n] are expected, such as: 

I<Example 1>:

    select Player, Points from my_db
        where (Rebounds > Assists) 

The following SQL operators can be used: =, <, >, <=, >=, <> 
as well as Perl's special operators: =~ and !~. The =~ and !~ 
operators are used to specify regular expressions, such as: 

I<Example 2>:

    select * from my_db
        where (Name =~ /Bird$/i) 

Selects records where the Name column ends with 
"Bird" (case insensitive). For more information, look at 
a manual on regexps. 

=item I<update> - updates records that match specified criteria. 

    update database set (cond1 OPERATOR value1)[,(cond2 OPERATOR value2)...]*
       where (cond1 OPERATOR value1)
       [and|or cond2 OPERATOR value2 ...] 

    * = This feature was added as of version 3.1.

I<Example>:

    update my_db 
    set Championships = (Championships + 1) 
        where (Player = 'Larry Joe Bird') 

   update my_db
        set Championships = (Championships + 1),
        Years = (12)

        where (Player = 'Larry Joe Bird')

=item I<delete> - removes records that match specified criteria:

    delete from database 
        where (cond1 OPERATOR value1) 
        [and|or cond2 OPERATOR value2 ...] 

I<Example>:

    delete from my_db
        where (Player =~ /Johnson$/i) or
              (Years > 12) 

=item I<alter> - simplified version of SQL-92 counterpart

Removes the specified column from the database. The 
other standard SQL functions for alter table are not 
supported:

    alter table database 
        drop column column-name 

I<Example>:

    alter table my_db 
        drop column Championships 

=item I<insert> - inserts a record into the database:

    insert into database 
        (col1, col2, ... coln) 
    values 
        (val1, val2, ... valn) 

I<Example>:

    insert into my_db 
        (Player, Years, Points, Championships) 
    values 
        ('Kareem Abdul-Jabbar', 21, 27, 5) 

I<Note:> You do not have to specify all of the fields in the 
database! Sprite also does not require you to specify 
the fields in the same order as that of the database. 

I<Note:> You should make it a habit to quote strings. 

=back

=head1 METHODS

Here are the four methods that are available:

=over 5

=item I<set_delimiter>

The set_delimiter function sets the read and write delimiter 
for the the SQL command. The delimiter is not limited to
one character; you can have a string, and even a regexp (for reading only).

I<Return Value>

None

=item I<set_os>

The set_os function can be used to notify Sprite as to the
operating system that you're using. Valid arguments are:
"UNIX", "VMS", "MSDOS", "NT" and "MacOS". UNIX is the default.

I<Return Value>

The previous OS value

=item I<sql>

The sql function is used to pass a SQL command to this module. All 
of the SQL commands described above are supported. The I<select> SQL 
command returns an array containing the data, where the first element
is the status. All of the other other SQL commands simply return a status.

I<Return Value>
    1 - Success
    0 - Error

=item I<close>

The close function closes the file, and destroys the database object. 
You can pass a filename to the function, in which case Sprite will 
save the database to that file. 

I<Return Value>

None

=back

=head1 EXAMPLES

Here are two simple examples that illustrate some of the functions of this
module:

=head2 I<Example 1>

    #!/usr/local/bin/perl5 

    use Sprite; 

    $rdb = new Sprite (); 

    # Sets the read delimiter to a comma (,) character. The delimiter
    # is not limited to one character; you can have a string, or even
    # a regexp.

    $rdb->set_delimiter ("Read", ","); 

    # Retrieves all records that match the criteria.

    @data = $rdb->sql (<<End_of_Query);

        select * from /shishir/nba
            where (Points > 25) 

    End_of_Query

    # Close the database and destroy the database object (i.e $rdb).
    # Since we did not pass a argument to this function, the data
    # is not updated in any manner.

    $rdb->close (); 

    # The first element of the array indicates the status.

    $status = shift (@data);
    $no_records = scalar (@data);

    if (!$status) {
    die "Sprite database error. Check your query!", "\n";
    } elsif (!$no_records) {
    print "There are no records that match your criteria!", "\n";
    exit (0);
    } else {
        print "Here are the records that match your criteria: ", "\n";

        # The database returns a record where each field is
        # separated by the "\0" character.

        foreach $record (@data) { 
            $record =~ s/\0/,/g;
            print $record, "\n";
        }
    } 

=head2 I<Example 2>

    #!/usr/local/bin/perl5 

    use Sprite; 

    $rdb = new Sprite (); 
    $rdb->set_delimiter ("Read", ","); 

    # Deletes all records that match the specified criteria. If the
    # query contains an error, Sprite returns a status of 1.

    $rdb->sql (<<Delete_Query) 
        || die "Database Error. Check your query", "\n";

        delete from /shishir/nba
            where (Rebounds <= 5) 

    Delete_Query

    # Access the database again! This time, select all the records that
    # match the specified criteria. The database is updated *internally*
    # after the previous delete statement.

    # Notice the fact that the full path to the database does not
    # need to specified after the first SQL command. This
    # works correctly as of version 3.1.

    @data = $rdb->sql (<<End_of_Query);

        select Player from nba
            where (Points > 25)

    End_of_Query

    # Sets the write delimiter to the (:) character, and outputs the
    # updated information to the file: "nba.new". If you do not pass
    # an argument to the close function after you update the database,
    # the modified information will not be saved.

    $rdb->set_delimiter ("Write", ":"); 
    $rdb->close ("nba.new"); 

    # The first element of the array indicates the status.

    $status = shift (@data);
    $no_records = scalar (@data);

    if (!$status) {
    die "Sprite database error. Check your query!", "\n";
    } elsif (!$no_records) {
    print "There are no records that match your criteria!", "\n";
    exit (0);
    } else {
        print "Here are the records that match your criteria: ", "\n";

        # The database returns a record where each field is
        # separated by the "\0" character.

        foreach $record (@data) { 
            $record =~ s/\0/,/g;
            print $record, "\n";
        }
    } 

=head1 ADVANTAGES

Here are the advantages of Sprite over mSQL by David Hughes available on
the Net: 

Allows for column names to be specified in the update command:

Perl's Regular Expressions allows for powerful pattern matching

The database is stored as text. Very Important! Information
can be added/modified/removed with a text editor.

Can add/delete columns quickly and easily

=head1 DISADVANTAGES

Here are the disadvantages of Sprite compared to mSQL: 

I<Speed>. No where close to mSQL! Sprite was designed to be 
used to manipulate very small databases (~1000-2000 records).

Does not have the ability to "join" multiple tables (databases) 
during a search operation. This will be added soon! 

=head1 RESTRICTIONS

=over 5

=item 1

If a value for a field contains the comma (,) character or the field 
delimiter, then you need to quote the value. Here is an example:

    insert into $database
    (One, Two)
    values
    ('$some_value', $two)

The information in the variable $some_value I<might> contain
the delimiter, so it is quoted -- you can use either the single
quote (') or the double quote (").

=item 2

All single quotes and double quotes within a value must be escaped.
Looking back at the previous example, if you think the variable
$some_value contains quotes, do the following:

    $some_value =~ s/(['"])/\\$1/g;

=item 3

If a field's value contains a newline character, you need to convert
the newline to some other character (or string):

    $some_value =~ s/\n/<BR>/g;

=item 4

If you want to search a field by using a regular expression:

    select * from $database
        where (Player =~ /Bird/i)

the only delimiter you are allowed is the standard one (i.e I</../>).
You I<cannot> use any other delimeter:

    select * from $database
        where (Player =~ m|Bird|i)

=item 5

Field names can only be made up of the following characters:

    "A-Z", "a-z", and "_"

In other words,
    
    [A-Za-z_]

=item 6

If your update value contains parentheses, you need to escape
them:

   $rdb->sql (<<End_of_Query);

    update my_db
        set Phone = ('\\(111\\) 222 3333')
        where (Name = /Gundavaram\$/i)

   End_of_Query

Notice how the "$" (matches end of line) is escaped as well!

=back

=head1 SEE ALSO

RDB (available at the Metronet Perl archive)

=head1 REVISION HISTORY

=over 5

=item v3.1 - June 18, 1996

Added the following features:

=over 3

=item *

As of this version,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          