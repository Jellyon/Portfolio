import tweepy
import json
from pymongo import MongoClient

api_key = 'RaPznFsCtVyqzJhzoUMVxSG5y'
api_key_secret = '1rlR8cYn06CCW7O2veU79oBPDpCPHEYfG2w1y57lgsDW3lCJtP'
access_token = '1504251071874670598-g7MG9E08RF12j16Fd6GEdCAzqMzXYq'
access_token_secret = 'K9TRTrayJb80no4Mnm2sl7TzX39utp5EhhtXiU1ZTd0d8'


class P3Listener(tweepy.Stream):
    def on_data(self, data):
        try:
            with open('p3.json', 'a+') as f:
                f.write(data.decode())
                f.write(',\n')
                return True
        except BaseException as e:
            print(str(e))
        return True


Twitter_stream = P3Listener(api_key, api_key_secret, access_token, access_token_secret)
Twitter_stream.filter(track=['#data'])

# tweet_client = MongoClient("mongodb://mymongodbconnstring!")
# db = tweet_client["project3"]
# collection = db["tweets"]
#
# with open('p3.json') as jfile:
#     jdata = json.load(jfile)
#
# collection.insert_many(jdata)
