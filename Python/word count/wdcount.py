import sys

if sys.argv.__len__() == 2:             # make sure you only enter 'wdcount.py "filename.extension"'
    try:                                # test the filename provided exists
        inFile = open(sys.argv[1], 'r')
    except:                             # on failure exits the program
        print('Error with filename, check spelling')
        exit(1)
else:
    print('Exiting, incorrect number of arguments')
    exit(2)

words = {}                              # dictionary to hold all words in the file
uniques = 0                             # counter for each unique word
total = 0                               # total words in the file
                                        # the default split() value separates by a space and \n
for word in inFile.read().split():      # assign a word to word then put it in the dictionary
    word = word.lower()                 # ignore the case of the words
    total += 1                          # would it be worse to use the value "words.__len__()"?
    if word not in words:
        words[word] = 1                 # if the word doesn't exist, it will set the value to 1
        uniques += 1
    else:
        words[word] += 1                # if the word exists already, it will increment the value by 1

none = 0                                # used to provide feedback if there are no words to show
for key in sorted(words, key=words.get, reverse=True):
    if key.endswith('in') and key.__len__() > 3:
        none += 1                       # iterated thru dict. in a sorted manner, prints if the word
        print(key, words[key])          # ends with 'in' and is 4+ characters
if not none:
    print('There were no words that have 4+ letters and end with \'in\'')
print('Unique words:', uniques)         # printing out the requirements of the assignment
print('Total words:', total)

inFile.close()                          # closes the file that was opened earlier
exit(0)
