import requests
import json

BASE_URL = "http://localhost:8080/api"


def send_request(method, endpoint, args=None, token=None):

    if args:
        for key in args.keys():
            print(f"{key} = {args[key]}")

    url = f"{BASE_URL}/{endpoint}"
    headers = {}
    if token:
        headers['Authorization'] = f'Bearer {token}'

    r = requests.request(method, url, allow_redirects=True, data=args, headers=headers)
    if not (200 <= r.status_code < 300):
        raise IOError(f"Request failed with {r.status_code} response.")

    return json.loads(r.text) if "application/json" == r.headers['Content-Type'] else r.text


if __name__ == '__main__':
    MAIL = "test+9@the-server.org"
    PASS = "super-secret"

    # Creating the user:
    response = send_request('POST', 'user', args={
        'email': MAIL,
        'password': PASS
    })
    print(f'New user ID: #{response["id"]}')

    # Authenticating:
    auth_token = send_request('POST', 'auth', args={
        'email': MAIL,
        'password': PASS
    })
    print(f'Token: {auth_token}')

    # Get my account details:
    account_detail = send_request('GET', 'user', token=auth_token)
    for key in account_detail:
        print(f"{key} = {account_detail[key]}")


