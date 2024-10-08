import sys
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression

object_columns = ['cut', 'color' , 'clarity']
numeric_columns = ['carat', 'depth', 'table', 'x', 'y', 'z']

carat = float(sys.argv[1])
cut = 'cut_'+sys.argv[2]
color = 'color_'+sys.argv[3]
clarity = 'clarity_'+sys.argv[4]
depth = float(sys.argv[5])
table = float(sys.argv[6])
x = float(sys.argv[7])
y = float(sys.argv[8])
z = float(sys.argv[9])

target = pd.read_csv("C:/close/shds/firstproject/SQL/case1.csv")
target.loc[0,'carat']=carat
target.loc[0, cut]=1
target.loc[0, color]=1
target.loc[0, clarity]=1
target.loc[0,'depth']=depth
target.loc[0,'table']=table
target.loc[0,'x']=x
target.loc[0,'y']=y
target.loc[0,'z']=z
dia_id=target.pop('dia_id')

df = pd.read_csv('C:/Laplace/LAST/AItheoryAndApplications/preprocessing/diamonds.csv')
df = df.reset_index(drop = True)
NUM_DATA_SET = df.price.isnull().sum()
y_true = df.pop('price')

for col_name in object_columns :
    df[col_name] = df[col_name].astype(object)
for col_name in numeric_columns :
    df[col_name] = df[col_name].astype(float)

def merge_and_get(ldf, rdf, on, how = 'inner', index=None) :
    if index is True :
        return pd.merge(ldf, rdf, how = how, left_index = True, right_index = True)
    else :
        return pd.merge(ldf, rdf, on = on, how = how)

one_hot_df = merge_and_get(df, pd.get_dummies(df['cut'], prefix = 'cut'), on = None, index = True)

for feature in object_columns[1:] :
    one_hot_df = merge_and_get(one_hot_df, pd.get_dummies(df[feature], prefix = feature), on = None, index = True)

dic_mean = {}
dic_std = {}

for feature in numeric_columns :
    dic_mean[feature] = df[feature].mean()
    dic_std[feature] = df[feature].std()
    df[feature] = (df[feature] - df[feature].mean())/(df[feature].std())

for feature in numeric_columns :
    target[feature] = (target[feature]-dic_mean[feature])/dic_std[feature]
df = pd.get_dummies(df, columns=['cut', 'clarity', 'color'])

def model_evaluation2(model) :
    model.fit(x_train, y_train)
    y_ = model.predict(target)
    return y_
x_train, x_test, y_train, y_test = train_test_split(df, y_true, test_size=0.3, random_state=0)

y_ = model_evaluation2(LinearRegression())
print(int(abs(y_[0])))