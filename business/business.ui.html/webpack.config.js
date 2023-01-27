const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'business': './src/main/js/business.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'business-theme': './src/main/js/business-theme.less',
    'business-theme-dark': './src/main/js/business-theme-dark.less'
  };

  return config;
};
