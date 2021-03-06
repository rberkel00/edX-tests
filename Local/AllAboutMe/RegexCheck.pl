#!/bin/bash/perl
use strict;
use warnings;

my $program;
open(my $fh, '<', "Work/AboutMe.java") or die "cannot open file AboutMe.java";
{
	local $/;
	$program = <$fh>;
}
close($fh);

print "1:true\n" if $program =~ /public[[:space:]]*class[[:space:]]*AboutMe/s;
print "2:true\n" if $program =~ /static[[:space:]]*void[[:space:]]*main[[:space:]]*\([[:space:]]*String[[:space:]]*(\[\])*[[:space:]]*[[:alnum:]]*[[:space:]]*(\[\])*[[:space:]]*\)/s;
print "3:true\n" if $program =~ /String[[:space:]]*myName[[:space:]]*\([[:space:]]*\)/s;
print "4:true\n" if $program =~ /String[[:space:]]*mySchool[[:space:]]*\([[:space:]]*\)/s;
print "5:true\n" if $program =~ /int[[:space:]]*myAge[[:space:]]*\([[:space:]]*\)/s;
print "6:true\n" if (($program =~ /[[:alnum:]]\.mySchool\(.*\)/s) && 
	($program =~ /[[:alnum:]]\.myAge\(.*\)/s) && 
	($program =~ /[[:alnum:]]\.myName\(.*\)/s));
print "7:true\n" if $program =~ /AboutMe[[:space:]]*\([[:space:]]*String[[:space:]]*[[:alnum:]]*[[:space:]]*,[[:space:]]*String[[:space:]]*[[:alnum:]]*[[:space:]]*,[[:space:]]*int[[:space:]]*[[:alnum:]]*[[:space:]]*\)/s;
print "8:true\n" if $program =~ /this\.[[:alnum:]]*.*this\.[[:alnum:]]*.*this\.[[:alnum:]]*/s;
print "9:true\n" if (
	($program !~ /String[[:space:]]*[[:upper:]]([[:lower:]]|[[:digit:]])/s) && 
	($program !~ /int[[:space:]]*[[:upper:]]([[:lower:]]|[[:digit:]])/s) && 
	($program !~ /double[[:space:]]*[[:upper:]]([[:lower:]]|[[:digit:]])/s) && 
	($program !~ /AboutMe[[:space:]]*[[:upper:]]([[:lower:]]|[[:digit:]])/s)
	);
print "10:true\n" if (
	($program !~ /{(?:[^\r\n]|\r(?!\n))*{/g) &&
	($program !~ /}(?:[^\r\n]|\r(?!\n))*}/g)
	);
