#!/usr/bin/python
import sys

prev_key = None
prev_count = 0
word = None
curr_count = 0
for line in sys.stdin:
    line = line.strip()
    word, curr_count = line.split('\t',1)

    try:
        curr_count = int (curr_count)
    
    except ValueError:
        continue

    if prev_key == word:
        prev_count += curr_count
    else:
        #if a previous key already exists
        if prev_key:
            print(f"{prev_key}\t{prev_count}")
        prev_key = word
        prev_count = curr_count

print(f"{prev_key}\t{prev_count}")

"""

yarn jar /opt/cloudera/parcels/CDH/lib/hadoop-mapreduce/hadoop-streaming.jar \
    -D mapred.jab.name="Streaming wordCount" \
    -D mapreduce.job.reduces=9 \
    -files mapper.py,reducer.py \
    -mapper "python2 mapper.py" \
    -combiner "python2 reducer.py" \
    -reducer "python2 reducer.py" \
    -input /data/wiki/en_articles_part \
    -output wcresult > /dev/null

    
    yarn jar yjar \
    -D mapred.jab.name="Streaming wordCount" \
    -D mapreduce.job.reduces=9 \
    -files mapper.py,reducer.py \
    -mapper "python mapper.py" \
    -combiner "python3 reducer.py" \
    -reducer "python3 reducer.py" \
    -input Ulysses.txt \
    -output wcresult > /dev/null
"""