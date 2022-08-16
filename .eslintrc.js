module.exports = {
    'root': true,
    'env': {
        'browser': true,
        'es2021': true,
    },
    'extends': [
        'google',
    ],
    'parserOptions': {
        'ecmaVersion': 'latest',
        'sourceType': 'script'
    },
    'rules': {
        'no-unused-vars': 2,
        'no-console': 1
    },
    'ignorePatterns': ['**/resources/js/plugin/**/*.js'],
};
