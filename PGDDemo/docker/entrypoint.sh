#!/bin/sh

set -e

REPOSITORY_DIR="${GIT_REPOSITORY_PATH:-/app/repository}"

echo "========================================"
echo " Git repository initialization"
echo "========================================"

echo "Repository directory: ${REPOSITORY_DIR}"
echo "Git host: ${GIT_HOST}"
echo "Git repository: ${GIT_REPOSITORY}"
echo "Git branch: ${GIT_BRANCH}"

mkdir -p "${REPOSITORY_DIR}"

git clone \
    --branch "${GIT_BRANCH}" \
    "https://${GIT_USERNAME}:${GIT_TOKEN}@${GIT_HOST}/${GIT_REPOSITORY}" \
    "${REPOSITORY_DIR}"

chmod +x "${REPOSITORY_DIR}/PGDDemo/mvnw"

echo "Repository cloned successfully."

echo "Current branch:"
git -C "${REPOSITORY_DIR}" branch --show-current

echo "Repository status:"
git -C "${REPOSITORY_DIR}" status --short

echo "========================================"
echo " Starting PGDDemo"
echo "========================================"

exec java -jar /app/app.jar